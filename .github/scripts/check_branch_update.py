#!/usr/bin/env python3
import subprocess
import sys
import os

def run_git_command(command):
    """Execute git command and return output"""
    try:
        result = subprocess.run(command, shell=True, capture_output=True, text=True, check=True)
        return result.stdout.strip()
    except subprocess.CalledProcessError as e:
        print(f"Git command failed: {command}")
        print(f"Error: {e.stderr}")
        sys.exit(1)

def check_branch_update():
    base_branch = os.getenv('GITHUB_BASE_REF', 'main')
    head_branch = os.getenv('GITHUB_HEAD_REF', '')
    
    print(f"Checking if PR branch '{head_branch}' is up to date with '{base_branch}'")
    
    # Fetch latest changes
    run_git_command(f"git fetch origin {base_branch}")
    run_git_command(f"git fetch origin {head_branch}")
    
    # Get merge base and base branch HEAD
    merge_base = run_git_command(f"git merge-base origin/{head_branch} origin/{base_branch}")
    base_head = run_git_command(f"git rev-parse origin/{base_branch}")
    
    print(f"Merge base: {merge_base}")
    print(f"Base branch HEAD: {base_head}")
    
    if merge_base != base_head:
        print("❌ PR branch is not up to date with base branch")
        print(f"Please update your branch with latest changes from {base_branch}")
        print("\nTo update your branch:")
        print(f"git checkout {head_branch}")
        print("git fetch origin")
        print(f"git rebase origin/{base_branch}")
        print(f"git push --force-with-lease origin {head_branch}")
        return False
    else:
        print("✅ PR branch is up to date with base branch")
        return True

if __name__ == "__main__":
    if not check_branch_update():
        sys.exit(1)
