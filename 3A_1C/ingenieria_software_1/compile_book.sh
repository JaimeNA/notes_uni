#!/bin/bash

pandoc --defaults=../../pandoc_defaults.yml book_notes/*.md -o book_notes.pdf
