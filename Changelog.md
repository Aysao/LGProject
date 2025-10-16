[2025-10-16] 
    # Réadaptation total du systeme de texturing, classe de textureManagement Opérationnel et pret à texturer toutes les mesh
    # l'utilisation simple de textureManager.activate juste avant un mech est suffisant pour afficher la texture sur le mesh après calcul d'uv
    # les Textures ont aussi des catalogues en hashmap permettant de faire retrouver les posistions UV en fonction du nom de l'image qui a été uploader en jeu
    # actuellement des id sont attribuer sur un enum qui permet de se retrouver sur quel slot un atlas est stocké