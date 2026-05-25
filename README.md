# Lab 8 — Threads, AsyncTask et Handler
**Cours : Programmation Mobile — Android avec Java**

---

## Objectif

Ce lab montre comment exécuter des traitements longs (chargement d'image, calcul lourd) **sans bloquer l'interface utilisateur**, en utilisant les mécanismes de concurrence Android : `Thread`, `Handler`, et `AsyncTask`.

---

## Concepts clés

| Concept | Rôle |
|---|---|
| **UI Thread** | Affiche l'écran, gère les clics. Ne doit jamais être bloqué. |
| **Worker Thread** | Exécute les tâches longues en arrière-plan. |
| **Handler** | Permet de poster du code sur le UI Thread depuis un Thread de fond. |
| **AsyncTask** | Gère automatiquement le cycle fond → UI via `doInBackground()`, `onProgressUpdate()`, `onPostExecute()`. |

---

## Structure du projet

```
app/
├── src/main/
│   ├── java/com/example/labthreadsasynctask/
│   │   └── MainActivity.java
│   └── res/layout/
│       └── activity_main.xml
```

---

## Fonctionnalités

- **Charger via Thread** : charge une image en arrière-plan via un `Thread` + `Handler` pour revenir sur le UI Thread
- **Lancer calcul AsyncTask** : exécute un calcul lourd avec une `ProgressBar` animée
- **Tester réactivité UI** : affiche un `Toast` immédiatement, même pendant un traitement en cours

---

## Captures d'écran

### 1. État initial — application prête

<img width="1080" height="2340" alt="Screenshot_20260525_012832_LabThreadsAsyncTask" src="https://github.com/user-attachments/assets/33cf81f8-0e75-45c9-9e63-4e4e0fae7e42" />

### 2. Après "Charger via Thread" — image chargée avec succès

<img width="1080" height="2340" alt="Screenshot_20260525_012840_LabThreadsAsyncTask" src="https://github.com/user-attachments/assets/536c2feb-1c5c-4f83-94ea-cb2a560098de" />

### 3. Après "Lancer calcul AsyncTask" — calcul terminé

<img width="1080" height="2340" alt="Screenshot_20260525_012849_LabThreadsAsyncTask" src="https://github.com/user-attachments/assets/8e4b7d89-4d7f-4c70-9393-8d53d405f75e" />

### 4. Toast "Interface toujours réactive !" — UI non bloquée

<img width="1080" height="2340" alt="Screenshot_20260525_012901_LabThreadsAsyncTask" src="https://github.com/user-attachments/assets/193e08b5-2517-4a94-9136-bcd5d588186d" />

---

## Résultats de validation

| Test | Résultat |
|---|---|
| Chargement image via Thread | Image affichée après ~1,2s |
| Calcul AsyncTask | ProgressBar de 0 à 100, résultat = 51599823 |
| Réactivité UI pendant traitement | Toast affiché immédiatement |

---

## Technologies utilisées

- Java
- Android SDK
- `Thread` + `Handler(Looper.getMainLooper())`
- `AsyncTask` (approche pédagogique)
- `BitmapFactory`
