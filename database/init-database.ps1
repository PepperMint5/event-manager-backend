$ErrorActionPreference = "Stop"

$DatabaseName = "event_manager"
$DatabaseUser = "postgres"

$ScriptDirectory = $PSScriptRoot

Write-Host "Creating database '$DatabaseName'..."

psql `
    -U $DatabaseUser `
    -d postgres `
    -v ON_ERROR_STOP=1 `
    -f "$ScriptDirectory\create-database.sql"

if ($LASTEXITCODE -ne 0) {
    throw "Failed to create database."
}

Write-Host "Creating tables..."

psql `
    -U $DatabaseUser `
    -d $DatabaseName `
    -v ON_ERROR_STOP=1 `
    -f "$ScriptDirectory\create-tables.sql"

if ($LASTEXITCODE -ne 0) {
    throw "Failed to create tables."
}

Write-Host "Populating initial data..."

psql `
    -U $DatabaseUser `
    -d $DatabaseName `
    -v ON_ERROR_STOP=1 `
    -f "$ScriptDirectory\populate-data.sql"

if ($LASTEXITCODE -ne 0) {
    throw "Failed to populate database."
}

Write-Host ""
Write-Host "Database '$DatabaseName' initialized successfully."