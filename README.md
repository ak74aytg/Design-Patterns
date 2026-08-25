# Cloud Storage SDK

A unified cloud storage SDK that provides a common client interface for **AWS S3, Google Cloud Storage, and Azure Blob Storage**, while using established creational design patterns to keep the implementation extensible, testable, and provider-agnostic.

## Overview

The SDK abstracts cloud-provider-specific storage implementations behind a consistent API. Applications can work with a single `StorageClient` interface without needing to know whether the underlying storage is AWS, GCP, or Azure.

The design demonstrates the following patterns:

| Pattern              | Class / Interface      | Purpose                                                                |
| -------------------- | ---------------------- | ---------------------------------------------------------------------- |
| **Singleton**        | `ConfigManager`        | Centralized configuration such as provider, region, and debug settings |
| **Builder**          | `StorageRequest`       | Creates immutable storage requests                                     |
| **Factory Method**   | `StorageClientFactory` | Creates a storage client based on a provider string                    |
| **Abstract Factory** | `CloudFactory`         | Creates a matched set of provider-specific clients and loggers         |

## Architecture

```text
                         CloudApp
                            |
                            v
                     ConfigManager
                     (Singleton)
                            |
                            v
                      CloudFactory
                    (Abstract Factory)
                       /          \
                      /            \
                     v              v
             StorageClient        Logger
                  |                 |
        +---------+---------+       |
        |         |         |       |
        v         v         v       v
      AWS        GCP      Azure   Provider
     Client     Client    Client   Logger
```

The application interacts primarily with abstractions:

```text
CloudApp
   |
   +--> CloudFactory
          |
          +--> StorageClient
          |
          +--> Logger
```

This prevents cloud-provider-specific implementation details from leaking into application code.

---

# Design Patterns

## 1. Singleton — `ConfigManager`

`ConfigManager` provides a single, globally accessible configuration instance.

It stores configuration such as:

* Cloud provider
* Region
* Debug flag
* Other SDK-level configuration

The implementation should use either:

* **Bill Pugh Singleton**, or
* **Double-Checked Locking**

### Recommended: Bill Pugh Singleton

The Bill Pugh approach provides lazy initialization without explicit synchronization on every call.

```java
public class ConfigManager {

    private ConfigManager() {
    }

    private static class Holder {
        private static final ConfigManager INSTANCE =
                new ConfigManager();
    }

    public static ConfigManager getInstance() {
        return Holder.INSTANCE;
    }

    // configuration methods...
}
```

### Why Singleton?

There should be one authoritative configuration source for the SDK. This avoids different parts of the application accidentally using conflicting provider, region, or debug settings.

---

## 2. Builder — `StorageRequest`

`StorageRequest` represents an immutable request to the storage system.

It must accept the following values in its constructor:

* `bucket`
* `key`
* `operation`

For example:

```text
StorageRequest
 ├── bucket
 ├── key
 └── operation
```

A builder can then be used to construct the immutable request:

```java
StorageRequest request = StorageRequest.builder()
        .bucket("documents")
        .key("reports/annual.pdf")
        .operation(Operation.GET)
        .build();
```

### Requirements

`StorageRequest` should:

* Be immutable
* Have final fields
* Validate required properties
* Expose getters but no setters
* Require `bucket`, `key`, and `operation`

This keeps requests predictable and safe to pass between components.

---

# 3. Factory Method — `StorageClientFactory`

`StorageClientFactory` creates a storage client based on a provider identifier.

For example:

```java
StorageClient client =
        StorageClientFactory.create("AWS");
```

The factory hides the provider-specific construction logic from the application.

Conceptually:

```text
"aws"   ──────> AWSS3Client
"gcp"   ──────> GCPStorageClient
"azure" ──────> AzureBlobClient
```

The application only depends on:

```java
StorageClient
```

rather than:

```java
AWSS3Client
GCPStorageClient
AzureBlobClient
```

This makes it easier to add another provider later without changing application-level code.

---

# 4. Abstract Factory — `CloudFactory`

`CloudFactory` creates a **family of related cloud-provider objects**.

The factory should provide methods similar to:

```java
public interface CloudFactory {

    StorageClient createStorageClient();

    Logger createLogger();
}
```

Each provider has its own implementation.

### AWS

```text
AWSCloudFactory
      |
      +----> AWSS3Client
      |
      +----> AWSLogger
```

The important requirement is that `AWSCloudFactory` creates the AWS storage client and AWS logger **together**.

```java
public class AWSCloudFactory implements CloudFactory {

    @Override
    public StorageClient createStorageClient() {
        return new AWSS3Client();
    }

    @Override
    public Logger createLogger() {
        return new AWSLogger();
    }
}
```

Likewise, other providers can have their own factories:

```text
GCPCloudFactory
      |
      +----> GCPStorageClient
      +----> GCPLogger

AzureCloudFactory
      |
      +----> AzureBlobClient
      +----> AzureLogger
```

This ensures that related objects always belong to the same cloud-provider family.

---

# `CloudApp`

`CloudApp` is the application-facing component.

It accepts a `CloudFactory` through its constructor rather than creating a provider-specific factory itself.

```java
public class CloudApp {

    private final CloudFactory cloudFactory;

    public CloudApp(CloudFactory cloudFactory) {
        this.cloudFactory = cloudFactory;
    }

    public void run() {
        ConfigManager config = ConfigManager.getInstance();

        String region = config.getRegion();

        Logger logger = cloudFactory.createLogger();
        StorageClient client = cloudFactory.createStorageClient();

        logger.info("Using region: " + region);

        // application logic...
    }
}
```

This demonstrates **dependency injection** in addition to the requested design patterns.

`CloudApp` does not need to know how an AWS, GCP, or Azure client is constructed.

---

# Example Usage

An AWS application could be initialized as follows:

```java
ConfigManager config = ConfigManager.getInstance();

config.setProvider("AWS");
config.setRegion("us-east-1");
config.setDebug(true);

CloudFactory factory = new AWSCloudFactory();

CloudApp app = new CloudApp(factory);

app.run();
```

A storage request can then be constructed independently:

```java
StorageRequest request = StorageRequest.builder()
        .bucket("my-bucket")
        .key("hello.txt")
        .operation(Operation.GET)
        .build();
```

The same application architecture can be used with GCP or Azure simply by supplying a different `CloudFactory`.

---

# Class Responsibilities

| Component              | Responsibility                                        |
| ---------------------- | ----------------------------------------------------- |
| `ConfigManager`        | Maintains global SDK configuration                    |
| `StorageRequest`       | Represents an immutable storage operation             |
| `StorageClient`        | Common abstraction for cloud storage                  |
| `AWSS3Client`          | AWS S3 implementation                                 |
| `GCPStorageClient`     | Google Cloud Storage implementation                   |
| `AzureBlobClient`      | Azure Blob Storage implementation                     |
| `Logger`               | Common logging abstraction                            |
| `AWSLogger`            | AWS-specific logger                                   |
| `GCPLogger`            | GCP-specific logger                                   |
| `AzureLogger`          | Azure-specific logger                                 |
| `StorageClientFactory` | Creates a storage client from a provider string       |
| `CloudFactory`         | Creates a provider-specific family of related objects |
| `AWSCloudFactory`      | Creates AWS storage client + AWS logger               |
| `CloudApp`             | Coordinates application behavior using abstractions   |

---


# Summary

This SDK uses four creational design patterns to provide a clean abstraction over multiple cloud-storage providers:

```text
Singleton
    └── ConfigManager
          └── Centralized configuration

Builder
    └── StorageRequest
          └── Immutable storage request

Factory Method
    └── StorageClientFactory
          └── Creates StorageClient from provider

Abstract Factory
    └── CloudFactory
          ├── StorageClient
          └── Logger
```

The resulting architecture keeps `CloudApp` independent of cloud-provider implementations while making the SDK easy to extend, test, and maintain.
