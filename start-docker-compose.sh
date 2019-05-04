#!/bin/bash

if [ $# -eq 0 ]; then
    echo "Nenhum parâmetro informado, executando 'up'"
    ARGS="up"
else
    ARGS="${@:1}"
fi

echo "Executando: docker-compose -f docker-compose.yml $ARGS"
docker-compose -f docker-compose.yml $ARGS

exit 0