#!/bin/bash
set -ev

git init
git status
git config --global push.default simple
git config --global user.email "travis@travis-ci.com"
git config --global user.name "Travis CI"
git checkout -b master
git add --all
git commit -am "Travis deploy"