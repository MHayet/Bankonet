insert into client(Nom, Prenom, Login, MDP, Identifiant) values
("Kirigaya","Kazuto","kirito","asuna","KirigayaKazuto"),
("Yuki","Asuna","asuna","kirito","YukiAsuna"),
("Kirigaya","Suguha","leaffa","alo","KirigayaSuguha");

insert into comptecourant(Numero, Intitule, Solde, DecouvertMaximum) values
("cckk01","cckk01",100,10),
("ccya01","ccya01",200,20);

insert into compteepargne(Numero, Intitule, Solde, TauxInteret) values
("ceya01","ceya01",400,4),
("ceks01","ceks01",800,4);

insert into clientcourant(IdentifiantClient, NumeroCompte) values
("KirigayaKazuto","cckk01"),
("YukiAsuna","ccya01");

insert into clientepargne(IdentifiantClient, NumeroCompte) values
("YukiAsuna","ceya01"),
("KirigayaSuguha","ceks01");