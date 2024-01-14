include('./demo-base-java/Tiltfile')

k8s_yaml('./tools/tracetest.yaml')
k8s_resource('tracetest', port_forwards=[11633])

k8s_yaml('./tools/kafka.yaml')
k8s_resource('kafka-controller', port_forwards=[9092])

local_resource('opentelemetry-cert-manager', 'kubectl apply -f https://github.com/cert-manager/cert-manager/releases/download/v1.11.0/cert-manager.yaml')
local_resource('opentelemetry-operator', 'kubectl apply -f https://github.com/open-telemetry/opentelemetry-operator/releases/latest/download/opentelemetry-operator.yaml', resource_deps=['tracetest', 'opentelemetry-cert-manager'])
local_resource('otel-collector', 'kubectl apply -f ./tools/otel-collector.yaml', resource_deps=['opentelemetry-operator'])

local_resource('run test', './test/./test.sh', resource_deps=['demo-base-java', 'tracetest', 'opentelemetry-collector'])