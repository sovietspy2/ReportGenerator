# ReportGenerator

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
create table listing_status (
	  id integer not null PRIMARY KEY ,
	  status_name varchar(255) NOT NULL
 	);

 	create table location (
 	  id varchar(255) not null PRIMARY KEY,
 	  manager_name varchar(255),
 	  phone varchar(50),
 	  address_primary varchar(100),
 	  address_secondary varchar(100),
 	  country varchar(50),
 	  town varchar(50),
 	  postal_code varchar(20)
	);

	create table marketplace (
	  id integer not null PRIMARY KEY,
	  marketplace_name varchar(255) not null
	);

	create table listing (
	id varchar(36) not null PRIMARY KEY,
	currency varchar(3) not null,
	description varchar(255) not null,
	listing_price DOUBLE,
	listing_status Integer NOT NULL,
	location_id varchar(255) not null,
	marketplace integer not null,
	title varchar(255) not null,
	owner_email_address varchar(255),
	quantity integer not null,
	upload_time datetime,
	FOREIGN KEY (listing_status) REFERENCES listing_status(id),
  FOREIGN KEY (location_id) REFERENCES location(id),
  FOREIGN KEY (marketplace) REFERENCES marketplace(id)
  );
```


