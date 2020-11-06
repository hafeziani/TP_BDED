M2 Bases de Données et Intelligence Artificielle
UE5 Bases de Donnees et Environnements Distribues\n 
Travaux Pratiques\n
Exercise 2
JOKAR Hafez

programme de simulation de gestion de comptes bancaires avec le pattern object factory
Remote Method Invocation (Java RMI)

- SERVER
    - Account
        - la création d'un compte
        - proposant les opérations:
            - dépot
            - retrait
            - consulation du solde
        - Archivage des opérations effectuées

    - Bank Interface
        - A remote object is an instance of a class that implements a remote interface. A remote interface extends the interface java.rmi.Remote and declares a set of remote methods. Each remote method must declare java.rmi.RemoteException (or a superclass of RemoteException) in its throws clause, in addition to any application-specific exceptions.
        
        - Remote method invocations can fail in many additional ways compared to local method invocations (such as network-related communication problems and server problems), and remote methods will report such failures by throwing a java.rmi.RemoteException.

    - Server Implementation
        - A "server" class, in this context, is the class which has a main method that creates an instance of the remote object implementation, exports the remote object, and then binds that instance to a name in a Java RMI registry. The class that contains this main method could be the implementation class itself, or another class entirely.
        
- CLIENT
    - invokes a method of the remote interface
    - The client program obtains a stub for the registry on the server's host, looks up the remote object's stub by name in the registry, and then invokes the methods on the remote object using the stub.


- OPERATIONS

- build.xml
    - fichierbuild.xmlpermettant de compiler le programme, de produire une archivejar autonome contenant le client et une autre contenant le serveur.
