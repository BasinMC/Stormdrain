[![License](https://img.shields.io/github/license/BasinMC/Stormdrain.svg?style=flat-square)](https://www.apache.org/licenses/LICENSE-2.0.txt)
[![Maven Central](https://img.shields.io/maven-central/v/org.basinmc/stormdrain.svg?style=flat-square)](https://search.maven.org/#search%7Cga%7C1%7Cg%3A%20org.basinmc%20a%3A%20stormdrain)
[![GitHub Release](https://img.shields.io/github/release/BasinMC/Stormdrain.svg?style=flat-square)](https://github.com/BasinMC/Stormdrain/releases)
[![CircleCI](https://img.shields.io/circleci/project/github/BasinMC/Stormdrain.svg?style=flat-square)](https://circleci.com/gh/BasinMC/Stormdrain)
[![Codacy grade](https://img.shields.io/codacy/grade/1705aebdf08541e5bd32e295c7665f77.svg?style=flat-square)](https://app.codacy.com/app/Basin/Stormdrain/)

Stormdrain
==========

GitHub Webhooks for Java

# Table of Contents

* [Usage](#usage)
* [Building](#building)
* [Contact](#contact)
* [Issues](#issues)
* [Contributing](#contributing)
* [License](#license)

Usage
-----

**Artifact Coordinates:** `org.basinmc:stormdrain:1.0-SNAPSHOT`<br />
**JavaDoc:** [Bytecode](https://www.javadoc.io/doc/org.basinmc/stormdrain/2.0)

```xml
<dependency>
  <groupId>org.basinmc</groupId>
  <artifactId>stormdrain</artifactId>
  <version>1.0</version>
</dependency>

<!-- For unstable releases: -->
<repository>
  <id>ossrh</id>
  <url>https://oss.sonatype.org/content/repositories/snapshots</url>
  
  <snapshots>
    <enabled>true</enabled>
  </snapshots>
</repository>

<dependency>
  <groupId>org.basinmc</groupId>
  <artifactId>stormdrain</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```

Events can be decoded either via Jackson directly (if you wish to use your web framework's built-in
decoder functionality, for instance) or via the `org.basinmc.stormdrain.PayloadType` enum:

```java
PayloadType type = ...;
ReleaseEvent event = type.decode(...);
```

Building
--------

1. Clone this repository via ```git clone https://github.com/BasinMC/Stormdrain.git``` or download a [zip](https://github.com/BasinMC/Stormdrain/archive/master.zip)
2. Build the library by running ```mvn clean install```
3. The resulting jars can be found in their respective ```target``` directories as well as your local maven repository

Contact
-------

* [IRC #Basin on EsperNet](http://webchat.esper.net/?channels=Basin)
* [Twitter](https://twitter.com/BasinMC)
* [GitHub](https://github.com/BasinMC/Stormdrain)

Issues
------

You encountered problems with the library or have a suggestion? Create an issue!

1. Make sure your issue has not been fixed in a newer version (check the list of [closed issues](https://github.com/BasinMC/Stormdrain/issues?q=is%3Aissue+is%3Aclosed)
1. Create [a new issue](https://github.com/BasinMC/Stormdrain/issues/new) from the [issues page](https://github.com/BasinMC/Stormdrain/issues)
1. Enter your issue's title (something that summarizes your issue) and create a detailed description containing:
   - What is the expected result?
   - What problem occurs?
   - How to reproduce the problem?
   - Crash Log (Please use a [Pastebin](https://gist.github.com) service)
1. Click "Submit" and wait for further instructions

Contributing
------------

Before you add any major changes to the library you may want to discuss them with us (see
[Contact](#contact)) as we may choose to reject your changes for various reasons. All contributions
are applied via [Pull-Requests](https://help.github.com/articles/creating-a-pull-request). Patches
will not be accepted. Also be aware that all of your contributions are made available under the
terms of the [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0.txt). Please read
the [Contribution Guidelines](CONTRIBUTING.md) for more information.

License
-------

This project is released under the terms of the
[Apache License](https://www.apache.org/licenses/LICENSE-2.0.txt), Version 2.0.

The following note shall be replicated by all contributors within their respective newly created
files (variables are to be replaced; E-Mail address or URL are optional):

```
Copyright <year> <first name> <surname <[email address/url]>
and other copyright owners as documented in the project's IP log.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

