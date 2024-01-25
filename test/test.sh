#!/bin/sh
tracetest run test --file ./test/order-service-hello.yaml --server-url http://localhost:11633/ --skip-result-wait --output pretty
tracetest run test --file ./test/order-service-correct-quantity.yaml --server-url http://localhost:11633/ --skip-result-wait --output pretty
tracetest run test --file ./test/order-service-correct-quantity-db.yaml --server-url http://localhost:11633/ --skip-result-wait --output pretty
tracetest run test --file ./test/order-service-too-much-quantity.yaml --server-url http://localhost:11633/ --skip-result-wait --output pretty
tracetest run test --file ./test/order-service-too-much-quantity-db.yaml --server-url http://localhost:11633/ --skip-result-wait --output pretty
tracetest run test --file ./test/warehouse-service-hello.yaml --server-url http://localhost:11633/ --skip-result-wait --output pretty
tracetest run test --file ./test/warehouse-service-reset.yaml --server-url http://localhost:11633/ --skip-result-wait --output pretty
tracetest run test --file ./test/warehouse-service-reset-db.yaml --server-url http://localhost:11633/ --skip-result-wait --output pretty