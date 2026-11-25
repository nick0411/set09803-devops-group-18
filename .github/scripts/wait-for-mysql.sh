#!/usr/bin/env bash

set -euo pipefail

CONTAINER="${MYSQL_CONTAINER:-world-db}"
USER_NAME="${DB_USER:-root}"
PASSWORD="${DB_PASSWORD:-rootpassword}"
DATABASE="${DB_NAME:-world}"
MAX_ATTEMPTS="${MAX_ATTEMPTS:-60}"
DELAY_SECONDS="${DELAY_SECONDS:-2}"

echo "Waiting for MySQL (${CONTAINER}) to be ready..."

for attempt in $(seq 1 "${MAX_ATTEMPTS}"); do

  if docker exec "${CONTAINER}" mysql \
       -u"${USER_NAME}" -p"${PASSWORD}" "${DATABASE}" \
       --silent --skip-column-names \
       -e "SELECT COUNT(*) FROM country" > /dev/null 2>&1; then
    echo "MySQL is ready (attempt ${attempt})."
    exit 0
  fi

  sleep "${DELAY_SECONDS}"
done

echo "MySQL was not ready after $((MAX_ATTEMPTS * DELAY_SECONDS)) seconds." >&2
docker logs --tail 50 "${CONTAINER}" >&2 || true
exit 1