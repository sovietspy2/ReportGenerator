# ReportGenerator

##### Used technologies:
- Gradle
- Java 8
- MsSQL 8
- JPA
- JUnit
- Spring Boot 2

### How the app works:
- Getting data from API
- Parsing JSON response to Entities
- Validating entities, valids are going to be saved and invalids are listed in error csv
- Generating report POJO map to JSON save to disk
- Upload report JSON file to FTP server

the application uses external properties.
vm options: `-Dspring.config.location="/config/config.properties" -Dlogging.config="/config/log4j2.xml"`

config.properties:
```
spring.jpa.hibernate.ddl-auto=none
spring.datasource.url=jdbc:mysql://localhost:3306/book?useSSL=false
spring.datasource.username=
spring.datasource.password=
listingStatusApiUrl=https://my.api.mockaroo.com/listingStatus?key=63304c70
listingApiUrl=https://my.api.mockaroo.com/listing?key=63304c70
locationApiUrl=https://my.api.mockaroo.com/location?key=63304c70
marketplaceApiUrl=https://my.api.mockaroo.com/marketplace?key=63304c70
csvFilePath=/csv/
tmpFilePath=/csv/report.json
ftpUsername=
ftpPassword=
ftpHost=
ftpPort=
reportName=report.json
importFileName=importLog.csv
```

log4j2.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN" monitorInterval="30">
    <Properties>
        <Property name="LOG_PATTERN">
            %d{yyyy-MM-dd HH:mm:ss.SSS} %5p ${hostName} --- [%15.15t] %-40.40c{1.} : %m%n%ex
        </Property>
    </Properties>
    <Appenders>
        <Console name="ConsoleAppender" target="SYSTEM_OUT" follow="true">
            <PatternLayout pattern="${LOG_PATTERN}"/>
        </Console>
        <RollingFile name="FileAppender" fileName="/config/application.log" filePattern="/config/application%d{yyyy-MM-dd}-%i.log">
            <PatternLayout>
                <Pattern>${LOG_PATTERN}</Pattern>
            </PatternLayout>
            <Policies>
                <SizeBasedTriggeringPolicy size="10MB" />
            </Policies>
            <DefaultRolloverStrategy max="10"/>
        </RollingFile>
    </Appenders>
    <Loggers>

         <Logger name="hu.wortex.report" level="INFO" additivity="false">
            <AppenderRef ref="FileAppender" />
            <AppenderRef ref="ConsoleAppender" />
        </Logger>

        <Root level="info">
            <AppenderRef ref="FileAppender" />
        </Root>
    </Loggers>

</Configuration>
```

SQL scripts:
```$xslt
create table listing_status
(
  id          integer   not null PRIMARY KEY,
  status_name text(255) NOT NULL
);

create table location
(
  id                varchar(36) not null PRIMARY KEY,
  manager_name      text(200),
  phone             text(50),
  address_primary   text(100),
  address_secondary text(100),
  country           text(50),
  town              text(50),
  postal_code       text(20)
);

create table marketplace
(
  id               integer  not null PRIMARY KEY,
  marketplace_name text(30) not null
);

create table listing
(
  id                  varchar(36) not null PRIMARY KEY,
  currency            text(3)     not null,
  description         text(255)   not null,
  listing_price       DOUBLE,
  listing_status      Integer     NOT NULL,
  location_id         varchar(36) not null,
  marketplace         integer     not null,
  title               text(255)   not null,
  owner_email_address text(255),
  quantity            integer     not null,
  upload_time         datetime,
  FOREIGN KEY (listing_status) REFERENCES listing_status (id),
  FOREIGN KEY (location_id) REFERENCES location (id),
  FOREIGN KEY (marketplace) REFERENCES marketplace (id)
);


```


#### possible erros and solutions
- After running the app once the API will probably give the same ID-s again so the DB should be wiped
- Sometimes the API gives duplicate marketplace names and that might cause SQL exception, delete the DB and run the app again for valid data
