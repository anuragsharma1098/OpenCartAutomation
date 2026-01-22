# Jenkins Pipeline Configuration Guide

This directory contains the Jenkins pipeline configuration for the OpenCart Automation test suite.

## Overview

The Jenkinsfile defines an automated CI/CD pipeline that:
- ✅ Checks out code from GitHub
- ✅ Builds the Maven project
- ✅ Runs automated tests
- ✅ Publishes test reports
- ✅ Archives artifacts

## File Structure

```
ci/
└── Jenkinsfile          # Jenkins pipeline definition
```

## Prerequisites

Before setting up Jenkins, ensure you have:

1. **Jenkins Server** installed and running
2. **Maven** plugin installed in Jenkins
3. **Git** plugin installed in Jenkins
4. **GitHub credentials** configured in Jenkins (if repo is private)
5. **Java 8+** installed on Jenkins agent
6. **Maven 3.6+** installed on Jenkins agent

## Jenkins Job Setup

### Step 1: Create a New Pipeline Job

1. Go to Jenkins Dashboard
2. Click **"New Item"**
3. Enter job name: `OpenCartAutomation`
4. Select **"Pipeline"** (not Freestyle)
5. Click **OK**

### Step 2: Configure Pipeline

In the job configuration page, scroll to the **"Pipeline"** section:

```
Definition: Pipeline script from SCM
```

### Step 3: Configure Git Repository

Fill in the SCM (Source Code Management) settings:

```
SCM: Git
├── Repository URL: https://github.com/anuragsharma1098/OpenCartAutomation.git
├── Credentials: (Add GitHub credentials if private)
├── Branch Specifier: */master
└── Script Path: ci/Jenkinsfile
```

### Step 4: Configure Build Triggers (Optional)

Choose how to trigger builds:

**Option 1: GitHub Webhook** (recommended for automatic builds on push)
```
GitHub hook trigger for GITScm polling ✓
```

**Option 2: Poll SCM** (scheduled builds)
```
Poll SCM: H H * * *  (Daily at midnight)
```

**Option 3: Manual** (Build Now button)
```
No triggers configured (manual trigger only)
```

### Step 5: Save & Run

1. Click **"Save"**
2. Click **"Build Now"** to test the pipeline
3. Watch the console output for any errors

## Pipeline Stages Explained

### 1. Checkout
```groovy
Clones your GitHub repository to the Jenkins workspace
```

### 2. Build
```groovy
Compiles Java code using: mvn clean compile -DskipTests
```

### 3. Test
```groovy
Runs automated tests using: mvn test -Dsuites=master.xml
```

### 4. Cross-Browser Test (Optional)
```groovy
Runs cross-browser tests when RUN_CROSS_BROWSER=true
Uses: mvn test -Dsuites=crossbrowsertesting.xml
```

## Test Suite Options

The pipeline uses TestNG XML suite files. You can modify the Maven command in the Jenkinsfile to run different suites:

| Suite | Command | Purpose |
|-------|---------|---------|
| **Master** | `mvn test -Dsuites=master.xml` | Default - runs all core tests |
| **Cross-Browser** | `mvn test -Dsuites=crossbrowsertesting.xml` | Tests across multiple browsers |
| **Docker Grid** | `mvn test -Dsuites=grid_docker.xml` | Runs tests on Selenium Grid |
| **Grouping** | `mvn test -Dsuites=grouping.xml` | Grouped test execution |

## Build Artifacts & Reports

After successful test execution, Jenkins publishes:

### Test Reports
- **Location**: Reports Dashboard in Jenkins UI
- **Files**: HTML test reports from `reports/` directory
- **TestNG Results**: XML format from `target/surefire-reports/`

### Archived Artifacts
- Test reports (`.html`)
- Surefire reports (`.xml`)
- All files available for download from build page

## Post-Build Actions

The pipeline automatically:

✅ **Publishes TestNG Results**
- Displays test summary on build page
- Shows pass/fail counts
- Links to detailed test logs

✅ **Archives HTML Reports**
- Stores test reports as build artifacts
- Accessible from build history

✅ **Sends Notifications** (if configured)
- Success notifications
- Failure alerts with logs

## Customizing the Pipeline

### Change Test Suite

Edit the Test stage in `ci/Jenkinsfile`:

```groovy
stage('Test') {
    steps {
        echo "========== Running Tests =========="
        sh 'mvn test -Dsuites=master.xml'  // ← Change this
    }
}
```

### Add Email Notifications

Add to post section:

```groovy
post {
    failure {
        emailext(
            subject: "Jenkins Build Failed",
            body: "Build failed. Check ${BUILD_URL}",
            to: "your-email@example.com"
        )
    }
}
```

### Increase Build Timeout

Edit the options section:

```groovy
options {
    timeout(time: 120, unit: 'MINUTES')  // Change 60 to desired minutes
}
```

## Troubleshooting

### Issue: "Script Path not found"
- **Solution**: Verify `Script Path: ci/Jenkinsfile` is correct
- Check file exists in your repository

### Issue: "mvn command not found"
- **Solution**: Configure Maven in Jenkins
- Go to Jenkins → Manage Jenkins → Global Tool Configuration
- Install Maven or specify custom path

### Issue: "Git command not found"
- **Solution**: Configure Git in Jenkins
- Go to Jenkins → Manage Jenkins → Global Tool Configuration
- Install Git or specify custom path

### Issue: Tests fail in Jenkins but pass locally
- **Solution**: 
  - Check browser driver availability
  - Run in headless mode
  - Verify test data files exist
  - Check configuration properties

## Environment Variables

The pipeline uses these environment variables:

```groovy
PROJECT_NAME = "OpenCartAutomation"
WORKSPACE_DIR = "${WORKSPACE}"  // Jenkins workspace path
```

## Jenkins Plugins Required

Install these plugins in Jenkins for full functionality:

1. **Pipeline** - For declarative pipelines
2. **Git** - For Git integration
3. **Maven Integration** - For Maven builds
4. **TestNG Results Plugin** - For test reporting
5. **HTML Publisher** - For HTML report publishing
6. **Email Extension** - For email notifications (optional)

## Best Practices

✅ **Use GitHub Webhooks** for automatic builds
✅ **Set Build Timeout** to prevent hanging builds
✅ **Archive Artifacts** for test report history
✅ **Use Parameterized Builds** for flexibility
✅ **Monitor Build Logs** for debugging
✅ **Keep Jenkinsfile in Version Control**

## Additional Resources

- [Jenkinsfile Documentation](https://www.jenkins.io/doc/book/pipeline/jenkinsfile/)
- [TestNG Documentation](https://testng.org/)
- [Maven Documentation](https://maven.apache.org/)
- [Selenium Documentation](https://www.selenium.dev/)

## Support

For issues or questions:
1. Check Jenkins build logs
2. Review test reports in Jenkins UI
3. Check GitHub repository for updates
4. Consult project documentation

---

**Last Updated**: January 22, 2026
**Project**: OpenCartAutomation
