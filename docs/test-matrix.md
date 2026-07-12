# ShopKart test matrix

| Scenario | UI | API | DB | Negative | Priority |
|---|---|---|---|---|---|
| Product search | Yes | Yes | - | - | P1 |
| Cart totals | - | Yes | Yes | - | P1 |
| Checkout E2E | Yes | Yes | Yes | - | P0 |
| Order access | - | Yes | - | 403 | P0 |
| Cancel rules | - | Yes | Yes | 409 | P1 |
| Out of stock | - | Yes | - | 409 | P2 |
