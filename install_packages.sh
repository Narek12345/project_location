#!/bin/bash
set -e
while IFS= read -r package || [ -n "$package" ]; do
    if [ -n "$package" ]; then
        if ! pip install "$package"; then
            echo "Ошибка при установке пакета: $package" >&2
            exit 1
        fi
        echo "Пакет установлен успешно: $package"
    fi
done < requirements.txt