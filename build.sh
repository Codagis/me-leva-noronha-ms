#!/bin/bash
# Script de build para Railway
# Garante que a pasta target seja sempre removida antes do build

set -e  # Para a execução em caso de erro

echo "🧹 Removendo pasta target existente..."
rm -rf target/

echo "📦 Executando Maven clean..."
mvn clean

echo "🔨 Executando Maven package..."
mvn package -DskipTests

echo "✅ Build concluído com sucesso!"

