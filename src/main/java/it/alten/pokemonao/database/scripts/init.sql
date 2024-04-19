-- Inserimento dei tipi di Pokémon
INSERT INTO pokemon_schema.type (name, icon_url)
VALUES
    ('Water', 'url_icona_water'),
    ('Dragon', 'url_icona_dragon'),
    ('Fire', 'url_icona_fire'),
    ('Normal', 'url_icona_normal');

-- Inserimento delle mosse
INSERT INTO pokemon_schema.move (name, power, type_id)
VALUES
    ('Tackle', 40, (SELECT id FROM pokemon_schema.type WHERE name = 'Normal')),
    ('Bubble', 40, (SELECT id FROM pokemon_schema.type WHERE name = 'Water')),
    ('Water Gun', 60, (SELECT id FROM pokemon_schema.type WHERE name = 'Water')),
    ('Withdraw', 0, (SELECT id FROM pokemon_schema.type WHERE name = 'Water')),
    ('Dragon Claw', 80, (SELECT id FROM pokemon_schema.type WHERE name = 'Dragon')),
    ('Wing Attack', 60, (SELECT id FROM pokemon_schema.type WHERE name = 'Dragon')),
    ('Thunder Punch', 75, (SELECT id FROM pokemon_schema.type WHERE name = 'Dragon')),
    ('Fire Punch', 75, (SELECT id FROM pokemon_schema.type WHERE name = 'Dragon')),
    ('Flamethrower', 90, (SELECT id FROM pokemon_schema.type WHERE name = 'Fire')),
    ('Fly', 90, (SELECT id FROM pokemon_schema.type WHERE name = 'Fire')),
    ('Dragon Rage', 80, (SELECT id FROM pokemon_schema.type WHERE name = 'Fire')),
    ('Slash', 70, (SELECT id FROM pokemon_schema.type WHERE name = 'Fire')),
    ('Hydro Pump', 110, (SELECT id FROM pokemon_schema.type WHERE name = 'Water')),
    ('Ice Beam', 90, (SELECT id FROM pokemon_schema.type WHERE name = 'Water')),
    ('Drill Peck', 80, (SELECT id FROM pokemon_schema.type WHERE name = 'Water')),
    ('Flash Cannon', 80, (SELECT id FROM pokemon_schema.type WHERE name = 'Water')),
    ('Transform', 0, (SELECT id FROM pokemon_schema.type WHERE name = 'Normal')),
    ('Earthquake', 100, (SELECT id FROM pokemon_schema.type WHERE name = 'Dragon')),
    ('Fire Fang', 65, (SELECT id FROM pokemon_schema.type WHERE name = 'Dragon'));

-- Inserimento dei Pokémon
INSERT INTO pokemon_schema.pokemon (name, image_url, current_hp, max_hp, trainer_name, type_id)
VALUES
    ('Squirtle', 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/shiny/7.png', 100, 100, 'Allenatore1', (SELECT id FROM pokemon_schema.type WHERE name = 'Water')),
    ('Dragonite', 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/149.png', 100, 100, 'Allenatore2', (SELECT id FROM pokemon_schema.type WHERE name = 'Dragon')),
    ('Charizard', 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/6.png', 100, 100, 'Allenatore3', (SELECT id FROM pokemon_schema.type WHERE name = 'Fire')),
    ('Empoleon', 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/395.png', 100, 100, 'Allenatore4', (SELECT id FROM pokemon_schema.type WHERE name = 'Water')),
    ('Ditto', 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/132.png', 100, 100, 'Allenatore5', (SELECT id FROM pokemon_schema.type WHERE name = 'Normal')),
    ('Garchomp', 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/445.png', 100, 100, 'Allenatore6', (SELECT id FROM pokemon_schema.type WHERE name = 'Dragon'));

-- Associazione delle mosse ai Pokémon
INSERT INTO pokemon_schema.pokemon_move (pokemon_id, move_id)
VALUES
    (1, (SELECT id FROM pokemon_schema.move WHERE name = 'Tackle')),
    (1, (SELECT id FROM pokemon_schema.move WHERE name = 'Bubble')),
    (1, (SELECT id FROM pokemon_schema.move WHERE name = 'Water Gun')),
    (1, (SELECT id FROM pokemon_schema.move WHERE name = 'Withdraw')),
    (2, (SELECT id FROM pokemon_schema.move WHERE name = 'Dragon Claw')),
    (2, (SELECT id FROM pokemon_schema.move WHERE name = 'Wing Attack')),
    (2, (SELECT id FROM pokemon_schema.move WHERE name = 'Thunder Punch')),
    (2, (SELECT id FROM pokemon_schema.move WHERE name = 'Fire Punch')),
    (3, (SELECT id FROM pokemon_schema.move WHERE name = 'Flamethrower')),
    (3, (SELECT id FROM pokemon_schema.move WHERE name = 'Fly')),
    (3, (SELECT id FROM pokemon_schema.move WHERE name = 'Dragon Rage')),
    (3, (SELECT id FROM pokemon_schema.move WHERE name = 'Slash')),
    (4, (SELECT id FROM pokemon_schema.move WHERE name = 'Hydro Pump')),
    (4, (SELECT id FROM pokemon_schema.move WHERE name = 'Ice Beam')),
    (4, (SELECT id FROM pokemon_schema.move WHERE name = 'Drill Peck')),
    (4, (SELECT id FROM pokemon_schema.move WHERE name = 'Flash Cannon')),
    (5, (SELECT id FROM pokemon_schema.move WHERE name = 'Transform')),
    (6, (SELECT id FROM pokemon_schema.move WHERE name = 'Dragon Claw')),
    (6, (SELECT id FROM pokemon_schema.move WHERE name = 'Earthquake')),
    (6, (SELECT id FROM pokemon_schema.move WHERE name = 'Fire Fang'));
