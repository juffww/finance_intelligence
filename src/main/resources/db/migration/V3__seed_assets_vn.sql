INSERT INTO assets (id, symbol, name, asset_type, price_currency, is_active, created_at)
VALUES
    (gen_random_uuid(), 'VIC',     'Tập đoàn Vingroup',                              'stock', 'VND', true, now()),
    (gen_random_uuid(), 'VHM',     'Công ty CP Vinhomes',                            'stock', 'VND', true, now()),
    (gen_random_uuid(), 'VNM',     'Công ty CP Sữa Việt Nam',                        'stock', 'VND', true, now()),
    (gen_random_uuid(), 'TCB',     'Ngân hàng TMCP Kỹ Thương Việt Nam',              'stock', 'VND', true, now()),
    (gen_random_uuid(), 'BID',     'Ngân hàng TMCP Đầu tư và Phát triển VN',         'stock', 'VND', true, now()),
    (gen_random_uuid(), 'VCB',     'Ngân hàng TMCP Ngoại thương Việt Nam',           'stock', 'VND', true, now()),
    (gen_random_uuid(), 'CTG',     'Ngân hàng TMCP Công Thương Việt Nam',            'stock', 'VND', true, now()),
    (gen_random_uuid(), 'HPG',     'Công ty CP Tập đoàn Hòa Phát',                   'stock', 'VND', true, now()),
    (gen_random_uuid(), 'MWG',     'Công ty CP Đầu tư Thế Giới Di Động',             'stock', 'VND', true, now()),
    (gen_random_uuid(), 'FPT',     'Công ty CP FPT',                                 'stock', 'VND', true, now()),
    (gen_random_uuid(), 'MSN',     'Công ty CP Tập đoàn Masan',                      'stock', 'VND', true, now()),
    (gen_random_uuid(), 'GAS',     'Tổng Công ty Khí Việt Nam',                      'stock', 'VND', true, now()),
    (gen_random_uuid(), 'SAB',     'Tổng Công ty CP Bia Rượu NGK Sài Gòn',           'stock', 'VND', true, now()),
    (gen_random_uuid(), 'ACB',     'Ngân hàng TMCP Á Châu',                          'stock', 'VND', true, now()),
    (gen_random_uuid(), 'MBB',     'Ngân hàng TMCP Quân Đội',                        'stock', 'VND', true, now()),
    (gen_random_uuid(), 'STB',     'Ngân hàng TMCP Sài Gòn Thương Tín',              'stock', 'VND', true, now()),
    (gen_random_uuid(), 'SSI',     'Công ty CP Chứng khoán SSI',                     'stock', 'VND', true, now()),
    (gen_random_uuid(), 'VND',     'Công ty CP Chứng khoán VNDirect',                'stock', 'VND', true, now()),

    -- Vàng
    (gen_random_uuid(), 'XAUUSD',  'Vàng thế giới (USD/oz)',                         'gold',  'USD', true, now()),
    (gen_random_uuid(), 'XAUVND',  'Vàng SJC (VND/lượng)',                           'gold',  'VND', true, now()),

    -- Chỉ số
    (gen_random_uuid(), 'VNINDEX', 'Chỉ số VN-Index',                                'index', 'VND', true, now()),
    (gen_random_uuid(), 'HNX',     'Chỉ số HNX-Index',                               'index', 'VND', true, now());