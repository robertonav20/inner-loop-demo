# Inner Loop Demo

### Hardware Requirements
 > - 4 CPU
 > - 8 GB ram
 
### Prerequisites
 > - docker
 > - kubectl
 > - k3d
 > - ctlptl
 > - Java 17
 > - Gradle 8.5
 
### Technology
 > - [Spring Initializr](https://start.spring.io/)
 > - [PostgreSQL](https://www.postgresql.org/)
 > - [Apache Kafka](https://kafka.apache.org/)
 > - [OpenTelemetry](https://opentelemetry.io/)
 > - [Ttracetest](https://tracetest.io/)
 > - [Tilt](https://tilt.dev/)
---

# tilt-example-java

[![Build Status](https://circleci.com/gh/tilt-dev/tilt-example-java/tree/master.svg?style=shield)](https://circleci.com/gh/tilt-dev/tilt-example-java)

An example project that demonstrates a live-updating Java server in Kubernetes. Read [doc](https://docs.tilt.dev/example_java.html).

We used [Spring Initializr](https://start.spring.io/) to bootstrap the project,
then added Docker & Kubernetes configs for running it in Kubernetes.

To run these examples, you should also have:
- javac (a JDK)
- unzip
- rsync
- python

## Fastest Deployment

This progression of examples shows how to start, and incrementally update
your project for live updates.

- [Demo Base Java](demo-base-java): The simplest way to start
- [Demo Advanced Java](demo-advanced-java): Live update executable Jars

## Other Configurations

- [101-jib](101-jib): An example of how to integrate Tilt with the [Jib Java
  image builder](https://github.com/GoogleContainerTools/jib)
- [102-jib-live-update](102-jib-live-update): An example of how to use
  live_update with Jib. It requires a lot of knowledge of Jib internals, but you
  can make it work!
- [103-micronaut](103-micronaut): An example of how to integrate Tilt with the
  [Micronaut framework](https://micronaut.io/).
- [201-quarkus-live-update](201-quarkus-live-update): An example of how to use
  live_update with [Quarkus](https://quarkus.io/), a container-first, hot-reloading framework for writing
  Java applications.
- [401-spring-boot-layertools](401-spring-boot-layertools): An example of how to
  further optimize the Spring Boot image with the latest recommendations from
  [the Spring Boot Docker
  guide](https://github.com/spring-guides/top-spring-boot-docker#spring-boot-layer-index).

# Tilt Avatars - Getting Started Sample Project

[![Build Status](https://circleci.com/gh/tilt-dev/tilt-avatars/tree/main.svg?style=shield)](https://circleci.com/gh/tilt-dev/tilt-avatars)

Tilt Avatars is a small sample project used by the Tilt Getting Started guide.

It consists of a Python web API backend to generate avatars and a Javascript SPA (single page app) frontend.
If you are not a Python or Javascript guru, don't panic!
The focus of this project is on introducing the `Tiltfile` and other Tilt concepts: the services are demonstrative to support the guide, but you do not need to understand the code within them to be successful.

We also know that no two projects are alike!
This project uses `Dockerfile`s with Docker as the build engine and `kubectl` friendly YAML files.
These only cover a small subset of Tilt functionality but have been chosen to minimize dependencies.

Even if you're using other technologies (e.g. `podman` or `helm`), we recommend starting here to learn the Tilt fundamentals.
After you're comfortable with how Tilt works, we've got a more comprehensive guide on authoring your first `Tiltfile` from scratch that covers much more.

## Running
You'll need to first install Tilt and prerequisites (Docker + local Kubernetes cluster).

Once you've installed Tilt, clone this repo and launch Tilt:
```sh
git clone https://github.com/tilt-dev/tilt-avatars.git
cd tilt-avatars
tilt up
```

## Need Help?
Join us on the Kubernetes Slack in `#tilt`!

## License

Copyright 2022 Docker, Inc.

Licensed under [the Apache License, Version 2.0](LICENSE)
