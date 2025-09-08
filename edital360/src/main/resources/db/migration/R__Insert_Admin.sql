INSERT INTO users(id, cpf, senha, nome_completo, created_at, role, email)
SELECT gen_random_uuid(),
       '11111111111',
       '$2a$10$wnVRc9BG3rmcnvQU1BVZU.kEHLHExG2f/fiKlb9.Chmxs9NFxcNpq',
       'Admin',
       now(),
       1,
       'admin@example.com'
    WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE cpf = '11111111111'
);
