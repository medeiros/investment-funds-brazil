## Investment Funds Brazil

[![TravisCI](https://travis-ci.com/medeiros/investment-funds-brazil.svg?branch=master)](https://travis-ci.com/medeiros/investment-funds-brazil)
[![Code Size](https://img.shields.io/github/languages/code-size/medeiros/investment-funds-brazil)](https://img.shields.io/github/languages/code-size/medeiros/investment-funds-brazil)
[![License](https://img.shields.io/github/license/medeiros/investment-funds-brazil)](https://img.shields.io/github/license/medeiros/investment-funds-brazil)

Handle investment funds datasets from CVM (Comissão de Valores
Mobiliários) for several kinds of analysis.

Adopts ETL concept for data ingestion and loading, and Microservices
concept for data exposal.

#### Adopted Tecnologies and Tools

|Framework/Library|Reason
|---|---|
Spring Boot|Basic building block to create Spring applications.
Spring Batch|To suppor the implementation of batch processing application.
Univocity CSV parser|To parse CSV files in a effecient way
Lombok|To reduce boilerplate code

|Tool|Reason
|---|---|
Arch Linux w/ i3m|Clean, out-of-the-way, efficient environment to development.
Idea IntelliJ|Java IDE, with Vim emulator capabilities ([1],[2])

[1]: https://www.maketecheasier.com/vim-keyboard-shortcuts-cheatsheet/
[2]: https://vim.rtorr.com/

## Installation

    $ mvn clean install

## Usage

    TBD

#### Run tests

You can type:

    $ mvn clean test

To run unit tests; and

    $ mvn clean integration-test

To run both unit and integration tests.


## Examples

    TBD

## Design choices

Some important design choices are defined in the table below:

|Category|Topic|Description|References
|---|---|---|---|
|Architecture & Design|DDD layered architecture|Adopted as a way decouple the domain problem (transactions, accounts and its business rules) from different levels of technical elements (csv parsing), so that the domain can evolve regardless of technical layers and those can be changed later without any impact to the domain. According to DDD layer theory, there are four layers (presentation, application, domain and infrastructure) and, as dependency rule, each layer cannot access the above layer. |[archfirst]|
|Architecture & Design|ETL model|This project adopts the common batch reference architecture being used for decades, and implemented in Spring Batch. |[spring-batch-arch]|
|Code best practices|Reducing boilerplate code|Using *lombok* library as a way to remove boilerplate code in Java. Lombok can generate getters, setters, builders and others, during compile-time, based on annotations.|[lombok]|
|Code best practices|TDD|A way to design and build classes that do just the necessary and nothing more. Almost all classes of this project were created using TDD technique.|[tdd]|
|Code best practices|Google Java Style Guide|Following an elegant idiom to code formatting, to ensure pleasant collaboration and better reading of code.|[google-guide]|
|VCS|Git Branching Model|A branch model is an organized way to structure a git project for further collaboration. In this project, all development will be made on `develop` branch, and merged back to `master` just when tag/release versions.|[git-branching]|
|VCS|Git Commit|Adopting best practices in Git commit messages. Very important for collaborative work.|[chris-beams]|

[git-branching]: https://nvie.com/posts/a-successful-git-branching-model/
[archfirst]: https://archfirst.org/domain-driven-design-6-layered-architecture/
[lombok]: https://projectlombok.org
[chris-beams]: https://chris.beams.io/posts/git-commit/
[google-guide]: https://google.github.io/styleguide/javaguide.html
[tdd]: https://www.amazon.com/Test-Driven-Development-Kent-Beck/dp/0321146530
[spring-batch-arch]: https://docs.spring.io/spring-batch/3.0.x/reference/html/domain.html#domainJob

#### Logical structure: layers

The packages' structure (in `src/main/java` dir) was designed as DDD layering (above described),
and it is as following:

|Package/Layer|Function|Allowed to access|Currently accessing
|---|---|---|---|
`com.arneam.ifbr.presentation` | Responsible to interact with the external world (users). This is where Main class is located. | itself, `application`, `domain` and `infrastructure` layers|itself and `application` layer
`com.arneam.ifbr.application` | Responsible to serialize and deserialize data, and orchestrate calls to domain's aggregated roots. Offers a Gateway entry-point to the presentation layer. This layer is stateless.|itself, `domain` and `infrastructure`|itself, `domain` and `infrastructure` layers
`com.arneam.ifbr.domain` |  Responsible to implement business rules. | itself and `infrastructure` layer | itself and `infrastructure` layer
`com.arneam.ifbr.infrastructure` | Responsible to interact with external world (data, integration with third party tools, etc), to hold framework integration classes, and to keep useful objects for the domain that are not part of the domain layer. | itself | itself

#### Glossary

Some concepts are here described for clarity:

|Concept|Explanation
|---|---|
Layer | Logical separation between classes, according to some defined concept. My concept here is related to [DDD] layered architecture, above described.
Aggregate Root | It is the central entity in a bounded context. according to [DDD].
Bounded context | A group of classes that solve a common business problem, according to [DDD].

[DDD]: https://www.amazon.com.br/dp/B00794TAUG/ref=dp-kindle-redirect?_encoding=UTF8&btkr=1

## License

Copyright © 2019 medeiros

Distributed under the MIT License.
