# muserver-otel
An example to test muserver for open telemetry

command to run the project -
java -javaagent:D:\\git\\muserver\\opentelemetry-javaagent.jar -Dotel.traces.exporter=zipkin -Dotel.metrics.exporter=none -Dotel.exporter.zipkin.endpoint=http://localhost:9411/api/v2/spans -Dotel.resource.attributes=service.name=mu-service -Dotel.instrumentation.experimental.span-suppression-strategy=span-kind  -jar target\muserver-1.0-SNAPSHOT.jar
