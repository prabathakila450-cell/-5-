# Fix Subject-Specific Question Paper PDFs and Data

## Problem
Currently, `TermTestPapersDataBank` cycles through the exact same set of 30 generic Google Drive PDF URLs for every subject (Mathematics, Science, History, Buddhism, Sinhala, English, Commerce, ICT, Civics, Geography, Health, Agriculture, Dancing, etc.), causing different subjects to point to the same PDF bundle while displaying subject titles.

## Proposed Solution
1. **Subject-Specific PDF URL Mapping**: Provide distinct, curated Google Drive PDF preview URLs for each major subject (Science, Mathematics, History, Buddhism, Sinhala, English, Commerce, ICT, Civics, Geography, Health, Agriculture, Dancing, etc.).
2. **Tailored Term Test & O/L Question Papers**: Ensure `TermTestPapersDataBank` and `GceOlPastPapersRepository` assign correct, subject-appropriate PDF resources and question snippets for each subject.
3. **Verification**: Compile the applet to verify clean build and syntax correctness.

## Proposed Changes
- **TermTestPapersDataBank.kt**: Update `DRIVE_PAPER_URLS` or map subject-specific Drive URL lists (e.g. Science URLs, Math URLs, History URLs, Commerce URLs, etc.) so that each subject gets its own dedicated PDF documents.
- **GceOlPastPapersRepository.kt**: Verify and ensure distinct official O/L paper links for each subject.
