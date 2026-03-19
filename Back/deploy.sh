#!/bin/bash

# Charge les variables secrets
source ./deploy.env

echo "🚀 Déploiement sur $SERVER_IP..."

ssh $SERVER_USER@$SERVER_IP << EOF

  # 1. Récupérer le projet
  if [ -d "$PROJECT_DIR" ]; then
    echo "🔄 Mise à jour du projet..."
    git -C $PROJECT_DIR pull origin PRO402-8
  else
    echo "📥 Clonage du projet..."
    git clone -b PRO402-8 https://$GITLAB_USER:$GITLAB_PASSWORD@git.uha4point0.fr/UHA40/fil-rouge-2024/4.0.2-de/julien-simon.git $PROJECT_DIR
  fi

  # 2. Créer le .env pour Docker
  echo "⚙️  Création du .env..."
  cat > $PROJECT_DIR/Back/.env << ENVEOF
MYSQL_ROOT_PASSWORD=$MYSQL_ROOT_PASSWORD
MYSQL_DATABASE=Fil_rouge_BDD
MYSQL_USER=$MYSQL_USER
MYSQL_PASSWORD=$MYSQL_PASSWORD
SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/Fil_rouge_BDD?useSSL=false&allowPublicKeyRetrieval=true
ENVEOF

  # 3. Lancer Docker
  echo "🐳 Lancement des conteneurs..."
  cd $PROJECT_DIR/Back
  docker compose down
  docker compose up --build -d

  # 4. Vérification finale
  echo "✅ État des conteneurs :"
  docker compose ps

EOF

echo ""
echo "🎉 Déploiement terminé !"
echo "👉 API accessible sur : http://$SERVER_IP:8080/storys"
