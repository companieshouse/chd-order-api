# Companies House chd-order-api

## chd-order-api
CHS API handling CRUD operations for ordering missing images through the Companies House Direct (CHD) database

### Requirements
* [Java 21][1]
* [Maven][2]
* [Git][3]

### Getting Started
1. Run `make` to build
2. Run `./start.sh` to run

### Environment Variables
Name | Description | Mandatory | Location
--- | --- | --- | ---
CHD_ORDER_API_PORT | Port this application runs on when deployed. | ✓ | start.sh
CHS_API_KEY | Key identifying this client for requests to internal APIs. |✓|env var|
CHPRD_DATASOURCE_URL | URL to CHPRD datasource |✓|env var|
CHPRD_SCHEMA_NAME | Name of CHPRD schema |✓|env var|
CHPRD_PASSWORD | Password to interact with DB |✓|env var|
CHPRD_CUSTOMER_ID | ORDERHEADER table record for customer id|✓|env var|
CHPRD_PAYMENT_METHOD | ORDERHEADER table record for payment method |✓|env var|
CHPRD_HANDCSR | ORDERHEADER table record for Hand CSR| ✓|env var|
CHPRD_STATUS| ORDERHEADER and ORDERDETAIL table record for status |✓|env var|
CHPRD_FLAGS | ORDERHEADER table record for flags |✓|env var|
CHPRD_LANGUAGE | ORDERHEADER table record for language |✓|env var|
CHPRD_DELIVMETH | ORDERDETAIL table record for delivery method |✓|env var|
CHPRD_DELIVLOCATION | ORDERDETAIL table record for delivery location |✓|env var|

### Endpoints
Path | Method | Description
--- | --- | ---
*`chd-order-api/healthcheck`* | GET | Returns HTTP OK (`200`) to indicate a healthy application instance.

[1]: https://www.oracle.com/java/technologies/downloads/#java21
[2]: https://maven.apache.org/download.cgi
[3]: https://git-scm.com/downloads

## Terraform ECS

### What does this code do?

The code present in this repository is used to define and deploy a dockerised container in AWS ECS.
This is done by calling a [module](https://github.com/companieshouse/terraform-modules/tree/main/aws/ecs) from terraform-modules. Application specific attributes are injected and the service is then deployed using Terraform via the CICD platform 'Concourse'.


Application specific attributes | Value                                | Description
:---------|:-----------------------------------------------------------------------------|:-----------
**ECS Cluster**        |order-service                                      | ECS cluster (stack) the service belongs to
**Load balancer**      |{env}-chs-apichgovuk-private                                        | The load balancer that sits in front of the service
**Concourse pipeline**     |[Pipeline link](https://ci-platform.companieshouse.gov.uk/teams/team-development/pipelines/image-redaction-api ) <br> [Pipeline code](https://github.com/companieshouse/ci-pipelines/blob/master/pipelines/ssplatform/team-development/chd-order-api )                                  | Concourse pipeline link in shared services


### Contributing
- Please refer to the [ECS Development and Infrastructure Documentation](https://companieshouse.atlassian.net/wiki/spaces/DEVOPS/pages/4390649858/Copy+of+ECS+Development+and+Infrastructure+Documentation+Updated) for detailed information on the infrastructure being deployed.

### Testing
- Ensure the terraform runner local plan executes without issues. For information on terraform runners please see the [Terraform Runner Quickstart guide](https://companieshouse.atlassian.net/wiki/spaces/DEVOPS/pages/1694236886/Terraform+Runner+Quickstart).
- If you encounter any issues or have questions, reach out to the team on the **#platform** slack channel.

### Vault Configuration Updates
- Any secrets required for this service will be stored in Vault. For any updates to the Vault configuration, please consult with the **#platform** team and submit a workflow request.

### Useful Links
- [ECS service config dev repository](https://github.com/companieshouse/ecs-service-configs-dev)
- [ECS service config production repository](https://github.com/companieshouse/ecs-service-configs-production)
