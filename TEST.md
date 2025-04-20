```mermaid
graph TD
    A[Пользовательский код] -->|High-level API| B[API Layer]
    B -->|Команды| C[Core Module]
    C -->|Управление| D[Аппаратура]
    D -->|Данные| C
    C -->|Отладка| E[Debug Module]
```