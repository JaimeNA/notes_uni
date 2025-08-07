#!/bin/bash

pandoc --defaults=../../pandoc_defaults.yml units/*.md -o notes.pdf
