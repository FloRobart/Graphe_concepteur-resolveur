#!/bin/bash

#-------------------------------------------------------------------------------#
# Liste les fichiers pour chaque dossiers et sous dossier du dossiers d'origine #
#-------------------------------------------------------------------------------#
listerDossiers()
{
    for i in `ls -l $1 | tr -s ' ' | cut -d ' ' -f 9` # permet de parcourir chaque éléments d'un répertoire
    do
        if [ -f $1"/"$i ]                             # vérifie si l'élément est un fichier             
        then
            ajoutFichiers $1 $2 $3 $i                 # appele la fonction ajoutFichier
        fi

        if [ -d $1"/"$i ]                             # vérifie si l'éléments est un dossier
        then
            source=$1"/"$i                            # modifie la source
            listerDossiers $source $2 $3              # appele la fonction listerDossier, pour le nouveau dossier
        fi
    done
}

#---------------------------------------------------------------#
# Ajoute le fichier à la compile list si l'extension correspond #
#---------------------------------------------------------------#
ajoutFichiers()
{
    extension=${4##*.}             # permet de récuperer tout ce qui est écrit derrière le dernier point de la variable i

    if [ "$extension" == "$2" ]    # vérifie si l'extension est valide
    then
        echo $1"/"$4 >> $3         # ajoute le fichier à la compile list
    fi
}

#-----------------------------------------#
# Vérifie si le dossier source est valide #
#-----------------------------------------#
verificationDossier()
{
    source="$1"           # affecte $1 à source
    until [ -d $source ]  # boucle jusqu'à ce que source soit un dossier valide
    do
        read -p "Veuillez entrez le dossier racine à partir du quel la compile.list vas être générer : " source
    done

    if [ -z $source ]     # si la source est null alors on prend le répertoire courrant
    then 
        source="."
    fi


> ./compile.list     # vide la compile list
    listerDossiers $source $extensionValide $nomFichierSortie  # appel listerDossiers 
}

#------#
# Main #
#------#
extensionValide="java"               
nomFichierSortie="./compile.list"


echo "Lancement de l'application..."
java -cp "$CLASSPATH:./bin:./bin/donnees/jar_libraries/jdom-2.0.6.jar:./bin/donnees/:." controleur.Controleur

if [ $? -eq 0 ]
then 

    echo "Fin de l'execution"

else
    echo "Compilation..."
    verificationDossier "./src"
    javac -cp "$CLASSPATH:./bin/donnees/jar_libraries/jdom-2.0.6.jar:./bin/donnees/:." -encoding utf8 -d "./bin" @compile.list

    if [ $? -eq 0 ]
    then 
        echo "Lancement du programme..."

        java -cp "$CLASSPATH:./bin:./bin/donnees/jar_libraries/jdom-2.0.6.jar:./bin/donnees/:." controleur.Controleur

        if [ $? -eq 0 ]
        then
            echo "Fin de l'execution"
        else
            echo "Erreur d'execution"
        fi

    else
        echo "Erreur de compilation"
    fi
fi

call java -cp "%CLASSPATH%;.\bin;.\bin\donnees\jar_libraries\jdom-2.0.6.jar;." controleur.Controleur 2> nul && ( echo Fin de l'execution. ) || ( echo Compilation... & call :genererCompileList ".\src" & call javac -cp "%CLASSPATH%;.\bin\donnees\jar_libraries\jdom-2.0.6.jar;." -encoding utf8 -d ".\bin" "@compile.list" && ( echo Lancement de l'application... & call java -cp "%CLASSPATH%;.\bin;.\bin\donnees\jar_libraries\jdom-2.0.6.jar;." controleur.Controleur ) && ( echo Fin de l'execution. ) || ( echo. & echo Erreur d'EXECUTION. ) ) || ( echo. & echo Erreur de COMPILATION. )

# Compilation
javac -cp "$CLASSPATH:./bin/donnees/jar_libraries/jdom-2.0.6.jar:./bin/donnees/:." -encoding utf8 -d "./bin" @compile.list

# lancement
java -cp "$CLASSPATH:./bin:./bin/donnees/jar_libraries/jdom-2.0.6.jar:./bin/donnees/:." controleur.Controleur