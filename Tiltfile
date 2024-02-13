k8s_yaml('./tools/tracetest.yaml')
k8s_resource('tracetest', port_forwards=[11633])

k8s_yaml('./tools/kafka.yaml')
k8s_resource('kafka-controller', port_forwards=[9092])

local_resource('opentelemetry-cert-manager', 'kubectl apply -f https://github.com/cert-manager/cert-manager/releases/download/v1.11.0/cert-manager.yaml')
local_resource('opentelemetry-operator', 'kubectl apply -f https://github.com/open-telemetry/opentelemetry-operator/releases/latest/download/opentelemetry-operator.yaml', resource_deps=['opentelemetry-cert-manager'])
local_resource('otel-instrumentation', 'kubectl apply -f ./tools/otel-instrumentation.yaml', resource_deps=['opentelemetry-operator'])

load('./order-service/Tiltfile', 'order_service')
load('./warehouse-service/Tiltfile', 'warehouse_service')
order_service(['tracetest'])
warehouse_service(['tracetest', 'kafka-controller', 'tracetest-postgresql'])

local_resource('run test', './test/./test.sh', auto_init=False, trigger_mode=TRIGGER_MODE_MANUAL, resource_deps=['order-service', 'warehouse-service', 'tracetest'])