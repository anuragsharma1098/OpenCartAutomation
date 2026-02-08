# GitHub Workflows

This folder contains GitHub Actions workflows for this repository.

- `ci.yml` — continuous integration workflow that runs `mvn clean test` on push and pull requests to `master` (matrix: Java 11 and 17). The workflow uploads test reports as an artifact named `test-reports`.

Usage
- Push changes or open a pull request targeting `master` to trigger the CI job.
- Download test artifacts from the workflow run's Artifacts section.

Customizing
- Edit the workflow at `.github/workflows/ci.yml` to change Java versions, trigger branches, or build steps.

Contact
- If you want a different workflow (e.g., run only on PRs, add badges, or deploy steps), open an issue or request a change.
