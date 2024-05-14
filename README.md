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
