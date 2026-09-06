### Added
- Port to 26.3-pre-2.
- Backport of `BlockItemId`
- Fixed `Identifier#parse` helper method being private.
- Port of 1.20.6+ common tags to be able to safely use them in multiloader.
- Added a helper interface, `Identifiable`, implemented on various classes that have an associated `Identifier` but do not have an obvious way to access it.