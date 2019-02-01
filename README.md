# ReportGenerator



config.properties:
```
spring.jpa.hibernate.ddl-auto=none
spring.datasource.url=jdbc:mysql://wortex.stream:3306/book?useSSL=false
spring.datasource.username=root
spring.datasource.password=
listing.status.url=https://my.api.mockaroo.com/listingStatus?key=63304c70
listing.url=https://my.api.mockaroo.com/listing?key=63304c70
location.url=https://my.api.mockaroo.com/location?key=63304c70
marketplace.url=https://my.api.mockaroo.com/marketplace?key=63304c70
csv.file.location=/csv/
tmp.file.path=/csv/report.json
ftp.username=qkac-online
ftp.password=kutya123
ftp.host=ftp.atw.hu
ftp.port=21
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

vm options: `-Dapp.config="/config/config.properties" -Dlogging.config="/config/log4j2.xml"`
