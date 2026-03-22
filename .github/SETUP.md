# Dependabot Setup Instructions

## Enable Auto-Merge on Repository

For auto-merge to work, you need to enable it in your repository settings:

1. Go to **Settings** → **General**
2. Scroll to **Pull Requests**
3. Check **Allow auto-merge**

## How It Works:

1. **Dependabot** runs weekly (Sunday at 10 AM) and groups all dependency updates into a single PR
2. PR is automatically **assigned to monikavalkova** (you'll receive GitHub email notifications)
3. **Build and tests** run automatically via the workflow
4. If tests pass, manually approve the auto-merge or set up branch protection rules for automatic merging

## Customization:

- Change schedule or assignees: Edit `assignees` and `schedule` in `.github/dependabot.yml`
- Adjust grouping: Modify `groups` patterns in `.github/dependabot.yml`
