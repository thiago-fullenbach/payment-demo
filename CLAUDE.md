# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project

PaymentDemo (Gradle root project name "Payment Demo") is a single-module Android app, package
`br.com.thiago.paymentdemo`, built with Kotlin and Jetpack Compose. It is in its very early stages:
the app currently boots to the default Compose "Hello Android" screen (`MainActivity.kt`), and a
small `domain` package (`Sale`, `SaleState`, `PaymentResult`) is being introduced to model a
payment/sale flow — `Sale` holds `id`, `amountCents`, `idempotencyKey`, and a `SaleState`
(`PENDING`, `APPROVED`, `DECLINED`, `UNKNOWN`); `PaymentResult` is a separate sealed class
(`Approved`, `Declined`, `NoAnswer`) representing the outcome of a payment attempt. These two types
are not yet wired together or connected to any UI/business logic.

## Commands

All commands run from the repo root using the Gradle wrapper (`gradlew.bat` on Windows PowerShell,
`./gradlew` in Bash).

- Build debug APK: `gradlew.bat assembleDebug`
- Run unit tests (JVM, in `app/src/test`): `gradlew.bat test`
- Run a single unit test class: `gradlew.bat test --tests "br.com.thiago.paymentdemo.ExampleUnitTest"`
- Run instrumented tests (in `app/src/androidTest`, requires a connected device/emulator): `gradlew.bat connectedAndroidTest`
- Lint: `gradlew.bat lint`
- Full check (lint + tests): `gradlew.bat check`

## Architecture notes

- Single Gradle module: `:app`. `minSdk = 24`, `targetSdk`/`compileSdk = 36`, Java 11
  source/target compatibility, Kotlin.
- UI is Jetpack Compose (`buildFeatures.compose = true`), Material3, using the Compose BOM for
  version alignment (see `gradle/libs.versions.toml` for dependency versions).
- `ui/theme/` holds standard generated Compose theme files (`Color.kt`, `Theme.kt`, `Type.kt`).
- `domain/` is the intended home for framework-free business logic/models (sales, payment
  results), kept separate from Compose UI code.
- Dependency versions are centralized in `gradle/libs.versions.toml` (version catalog) and
  referenced via `libs.*` aliases in `app/build.gradle.kts` — add new dependencies there rather
  than hardcoding coordinates.
