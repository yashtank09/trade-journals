# Changelog

All notable changes to this project will be documented in this file.

## [Unreleased]

### Refactor
- **Architecture**: Initiated restructuring project from `org.tradebook.tradeprocessor` to `org.tradebook.journal`.
- **Package Structure**: Adopting a modular monolith structure with feature-based packages:
  - `features/ingestion`: Legacy file processing logic.
  - `features/auth`: Security and User management.
  - `features/journal`: Core trade logic and matching.
  - `features/analytics`: Dashboard and performance metrics.
  - `common`: Shared DTOs, utilities, and configurations.

### Added
- Created `CHANGELOG.md` to track architectural changes.
