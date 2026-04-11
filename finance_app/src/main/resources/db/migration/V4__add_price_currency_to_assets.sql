-- Add missing currency column for existing assets table.
-- Keep this migration forward-only; do not renumber old versions once applied.
ALTER TABLE IF EXISTS assets
ADD COLUMN IF NOT EXISTS price_currency varchar(3) NOT NULL DEFAULT 'VND';

