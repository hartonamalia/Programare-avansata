*Clasa GameServer imi initializeaza server-ul la portul 8100 si asteapta conexiuni cu clienti
*De asemenea am creat serverTimeout si clientTimeout ce seteaza timpul apelului blocant
*Creez un thread pentru fiecare client ce solicita conexiunea
*Clasa ClientThread citeste cererile clientului
*La citirea comenzii stop, transmite comanda de inchidere a server-ului