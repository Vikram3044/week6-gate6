# week6-gate6

## ShopKart automation

Java 21 automation framework for the Week 6 ShopKart pre-capstone. It keeps API clients, UI page objects, XPath templates, builders, database support, and Cucumber glue separate.

## Prerequisites

- Java 21 and Docker
- ShopKart API running at `http://localhost:8080`; when using Vite locally, the UI runs at `http://localhost:5173`
- `SHOPKART_ALICE_PW` and `SHOPKART_BOB_PW` set in your environment (or a git-ignored `secrets.local.properties` file)

Run `gradle test`. Generate Allure with `gradle allureReport` then `gradle allureServe`.

The API/UI exercise the running ShopKart app. `MySqlSupport` starts an isolated MySQL database and runs Flyway migrations. For DB assertions, use either `SHOPKART_DB_URL` / `SHOPKART_DB_USER` / `SHOPKART_DB_PASSWORD`, or the supplied app's `DB_HOST` / `DB_PORT` / `DB_NAME` / `DB_USER` / `DB_PASSWORD` through the secret-file option below.

When this workspace contains the supplied `sdet-retail-app/.env`, the resolver discovers it automatically. To use a different secret file, keep it outside this project and point the resolver to it for the current PowerShell session:

```powershell
$env:SHOPKART_SECRETS_FILE = "..\sdet-retail-app\.env"
gradle test
```

The resolver supports both `SHOPKART_ALICE_PW` and the app's `SHOPKART_ALICE_PASSWORD` naming; neither is logged or attached to Allure.

## GitHub Actions CI

The workflow in `.github/workflows/ci.yml` starts the supplied ShopKart SUT against a disposable MySQL 8.4 service, runs the Gradle suite, and publishes the generated Allure site to the `gh-pages` branch after successful pushes to `main`. It also restores the prior report's `history/` directory so Allure trends persist.

Add these repository secrets before enabling CI:

- `SHOPKART_ALICE_PASSWORD`
- `SHOPKART_BOB_PASSWORD`
- `SHOPKART_CAROL_PASSWORD`
- `SHOPKART_DB_PASSWORD`
- `SHOPKART_DB_ROOT_PASSWORD`

The workflow does not commit or print any of these values.

In the GitHub repository settings, enable **Pages** and select **Deploy from a branch** → `gh-pages` → `/(root)`. The report will then be available at `https://Vikram3044.github.io/week6-gate6/` after the first successful CI run.
