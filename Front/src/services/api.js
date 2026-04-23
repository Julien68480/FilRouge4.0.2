import axios from 'axios';

const API_BASE = import.meta.env.VITE_API_URL; 

export const api = axios.create({
  baseURL: API_BASE,
  headers: { 'Content-Type': 'application/json' }
});

// on utilise la variable d'environnement VITE_API_URL pour définir l'URL de base de l'API, 
// ce qui permet de facilement changer l'URL en fonction de l'environnement (développement, production, etc.) 
// sans modifier le code.

//axios.create() est utilisé pour créer une instance d'axios avec une configuration par défaut,

//toute les requêtes effectuées avec cette instance utiliseront l'URL de base et les en-têtes définis ici.