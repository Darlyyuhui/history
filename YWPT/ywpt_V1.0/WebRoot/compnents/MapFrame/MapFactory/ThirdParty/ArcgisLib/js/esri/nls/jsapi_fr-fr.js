/*
 COPYRIGHT 2009 ESRI

 TRADE SECRETS: ESRI PROPRIETARY AND CONFIDENTIAL
 Unpublished material - all rights reserved under the
 Copyright Laws of the United States and applicable international
 laws, treaties, and conventions.

 For additional information, contact:
 Environmental Systems Research Institute, Inc.
 Attn: Contracts and Legal Services Department
 380 New York Street
 Redlands, California, 92373
 USA

 email: contracts@esri.com
 */
//>>built
define("esri/nls/jsapi_fr-fr",{"dijit/form/nls/validate":{"rangeMessage":"Cette valeur n'est pas comprise dans la plage autorisée.","invalidMessage":"La valeur indiquée n'est pas correcte.","missingMessage":"Cette valeur est requise."},"esri/nls/jsapi":{"identity":{"noAuthService":"Impossible d’accéder au service d’authentification.","lblCancel":"Annuler","lblUser":"Nom d´utilisateur :","title":"Se connecter","forbidden":"Le nom d’utilisateur et le mot de passe sont valides, mais vous n’êtes pas autorisé à accéder cette ressource.","errorMsg":"Nom d'utilisateur/mot de passe non valides. Réessayez.","lblItem":"élément","lblOk":"OK","info":"Veuillez vous connecter pour accéder à l´élément sur ${server} ${resource}","lblSigning":"Connexion…","invalidUser":"Le nom d’utilisateur ou le mot de passe que vous avez entré est incorrect.","lblPwd":"Mot de passe :"},"map":{"deprecateShiftDblClickZoom":"Map.(enable/disable)ShiftDoubleClickZoom obsolète. Le comportement de Shift-Double-Clic ne sera pas pris en compte.","deprecateReorderLayerString":"Map.reorderLayer(/*String*/ id, /*Number*/ index) obsolète. Utiliser Map.reorderLayer(/*Layer*/ layer, /*Number*/ index)."},"virtualearth":{"vetiledlayer":{"bingMapsKeyNotSpecified":"BingMapsKey doit être indiqué."},"vegeocode":{"bingMapsKeyNotSpecified":"BingMapsKey doit être indiqué.","requestQueued":"Impossible de récupérer le jeton du serveur. Mise en file d'attente de la requête à exécuter une fois le jeton récupéré du serveur."}},"bufferTool":{"sizeHelp":"Pour créer plusieurs zones tampon, séparez les distances par des espaces (2 3 5).","typeLabel":"Type de zone tampon","disks":"Disques","round":"Ronde","right":"Droite","distanceMsg":"Valeurs numériques uniquement","itemDescription":"Service d’entités généré à partir de la solution de Entités de zone tampon. L’entrée de ${layername} a été ajoutée à la zone tampon avec ${distance_field} ${units}","resultLabel":"Nom de la couche résultat","around":"Les deux","sideType":"Type de côté","flat":"Plate","multipleDistance":"Les zones tampon de distance multiples devraient être","outputLayerName":"Zone tampon de ${layername}","rings":"Anneaux","sizeLabel":"Entrer la taille de la zone tampon","itemTags":"Résultat d’analyse, Zone tampon, ${layername}","areaofInputPoly":"Polygones d'origine dans les zones tampon","left":"Gauche","bufferDefine":"Créer des zones tampon à partir de <b>${layername}</b>","distance":"Distance","itemSnippet":"Service d’entités Analyse généré à partir de la zone tampon","endType":"Type d'extrémité","field":"Champ","optionsLabel":"Options","include":"Ajouter","exclude":"Exclure","dissolve":"Fusionnée","overlap":"Superposée"},"widgets":{"attributeInspector":{"NLS_title":"Modifier des attributs","NLS_validationFlt":"La valeur doit être un nombre réel.","NLS_noFeaturesSelected":"Aucune entité sélectionnée","NLS_validationInt":"La valeur doit être un nombre entier.","NLS_next":"Suivante","NLS_errorInvalid":"Non valide","NLS_previous":"Précédente","NLS_first":"Premier","NLS_deleteFeature":"Supprimer","NLS_of":"de","NLS_last":"Dernier"},"legend":{"NLS_polygons":"Polygones","NLS_lines":"Lignes","NLS_noLegend":"Pas de légende","NLS_points":"Points","NLS_creatingLegend":"Création de la légende"},"directions":{"getDirections":"Itinéraire","findOptimalOrder":"Optimiser le tri","showOptions":"Afficher les options","reverseDirections":"Itinéraire inverse","addDestination":"Ajouter une destination","returnToStart":"Revenir au départ","error":{"maximumStops":"Le nombre maximal d´arrêts est atteint.","notEnoughStops":"Entrez une origine et une destination.","invalidStopType":"Type d´arrêt non valide","locator":"Emplacement introuvable.","noAddresses":"Aucune adresse renvoyée.","unknownStop":"Emplacement <name> introuvable.","noStops":"Aucun arrêt donné à ajouter.","routeTask":"Impossible de créer un itinéraire vers ces adresses.","locatorUndefined":"Géocodage inverse impossible. URL non définie."},"hideOptions":"Masquer les options","units":{"KILOMETERS":{"name":"kilomètres","abbr":"km"},"MILES":{"name":"milles","abbr":"mi"},"METERS":{"name":"mètres","abbr":"m"},"NAUTICAL_MILES":{"name":"milles nautiques","abbr":"mn"}},"useTraffic":"Utiliser le trafic","time":{"minute":"minute","minutes":"minutes","hour":"heure","hours":"heures"},"printNotes":"Entrez les notes ici","viewFullRoute":"Zoom sur l’itinéraire entier","printDisclaimer":"L’itinéraire est fourni uniquement pour prévoir votre trajet et reste soumis aux <a href='http://www.esri.com/legal/software-license' target='_blank'>conditions d’utilisation d’Esri</a>. Le caractère fluctuant des conditions de circulation doit être pris en compte et peut affecter la précision des informations de l’itinéraire. Tenez-en compte, ainsi que de la signalisation routière et du code de la route. Tous les risques d´utilisation sont sous votre responsabilité.","print":"Imprimer"},"timeSlider":{"NLS_previous":"Précédente","NLS_play":"Lecture/Pause","NLS_next":"Suivante","NLS_invalidTimeExtent":"TimeExtent non précisé ou le format est incorrect.","NLS_first":"Premier"},"templatePicker":{"loading":"Chargement en cours...","creationDisabled":"La création d´entités est désactivée pour toutes les couches."},"editor":{"tools":{"NLS_pointLbl":"Point","NLS_reshapeLbl":"Remodeler","NLS_arrowLeftLbl":"Flèche gauche","NLS_triangleLbl":"Triangle","NLS_autoCompleteLbl":"Automatique","NLS_arrowDownLbl":"Flèche bas","NLS_selectionRemoveLbl":"Soustraire de la sélection","NLS_unionLbl":"Agréger","NLS_freehandPolylineLbl":"Polyligne à main levée","NLS_rectangleLbl":"Rectangle","NLS_ellipseLbl":"Ellipse","NLS_attributesLbl":"Attributs","NLS_arrowUpLbl":"Flèche haut","NLS_arrowRightLbl":"Flèche droite","NLS_undoLbl":"Annuler","NLS_arrowLbl":"Flèche","NLS_cutLbl":"Couper","NLS_polylineLbl":"Polyligne","NLS_selectionClearLbl":"Effacer la sélection","NLS_polygonLbl":"Polygone","NLS_selectionUnionLbl":"Agréger","NLS_freehandPolygonLbl":"Polygone à main levée","NLS_deleteLbl":"Supprimer","NLS_extentLbl":"Etendue","NLS_selectionNewLbl":"Nouvelle sélection","NLS_circleLbl":"Cercle","NLS_redoLbl":"Répéter","NLS_selectionAddLbl":"Ajouter à la sélection"}},"mosaicRule":{"queryOperatorLabel":"Opérateur :","refreshLockRasterIdsLabel":"Actualiser","recognizedMosaicMethodsList":"Aucun,Par attribut,Centre,Point nadiral,Verrouiller le raster,Nord-Ouest,Ligne de raccord","selectAllLabel":"Tout sélectionner","lockRasterRequestErrorMsg":"Erreur de recherche...","lockRasterRequestDoneMsg":"Terminé...","lockRasterRequestNoRasterMsg":"Aucun raster trouvé...","mosaicOperatorLabel":"Opérateur de mosaïque :","mosaicMethodLabel":"Méthode de mosaïquage :","lockRasterIdLabel":"Verrouiller l’ID de raster :","orderValueLabel":"Valeur de tri :","queryFieldLabel":"Champ :","orderFieldNotFound":"Non disponible","ascendingLabel":"Croissant :","lockRasterRequestMsg":"Recherche...","queryLabel":"Requête :","orderFieldLabel":"Champ de tri :","queryValueLabel":"Valeur :"},"attachmentEditor":{"NLS_error":"Une erreur s'est produite.","NLS_attachments":"Pièces jointes :","NLS_none":"Aucune","NLS_add":"Ajouter","NLS_fileNotSupported":"Ce type de fichier n'est pas pris en charge."},"overviewMap":{"NLS_invalidSR":"la référence spatiale de la couche donnée n'est pas compatible avec la carte principale","NLS_invalidType":"type de couche non pris en charge. Les types valides sont 'TiledMapServiceLayer' et 'DynamicMapServiceLayer'","NLS_noMap":"'map' introuvable dans les paramètres en entrée","NLS_hide":"Masquer la vue d'ensemble de la carte","NLS_drag":"Faites glisser le curseur pour modifier l'étendue de la carte","NLS_maximize":"Agrandir","NLS_noLayer":"carte principale sans couche de base","NLS_restore":"Restaurer","NLS_show":"Afficher la vue d'ensemble de la carte"},"measurement":{"NLS_length_kilometers":"Kilomètres","NLS_area_sq_miles":"Milles carrés","NLS_length_yards":"Verges","NLS_distance":"Distance","NLS_area_acres":"Acres","NLS_resultLabel":"Résultat de la mesure","NLS_length_miles":"Milles","NLS_area_hectares":"Hectares","NLS_deg_min_sec":"DMS","NLS_area":"Surface","NLS_area_sq_meters":"Mètres carrés","NLS_latitude":"Latitude","NLS_area_sq_kilometers":"Kilomètres carrés","NLS_area_sq_feet":"Pieds carrés","NLS_longitude":"Longitude","NLS_location":"Emplacement","NLS_decimal_degrees":"Degrés","NLS_length_feet":"Pieds","NLS_area_sq_yards":"Verges carrés","NLS_length_meters":"Mètres","NLS_map_coordinate":"Coordonnées de la carte"},"bookmarks":{"NLS_add_bookmark":"Ajouter un géosignet","NLS_new_bookmark":"Sans titre","NLS_bookmark_edit":"Modifier","NLS_bookmark_remove":"Supprimer"},"renderingRule":{"rendererLabelTitle":"Moteur de rendu :","bandCombinationLabelTitle":"Combinaison des canaux :","bandNamesRequestMsg":"Demande d’informations sur les canaux..."},"Geocoder":{"main":{"geocoderMenuButtonTitle":"Changer de géocodeur","untitledGeocoder":"Géocodeur sans intitulé","clearButtonTitle":"Effacer la recherche","searchButtonTitle":"Rechercher","geocoderMenuCloseTitle":"Fermer le menu","geocoderMenuHeader":"Sélectionner un géocodeur"},"esriGeocoderName":"Esri World Geocoder"},"popup":{"NLS_attach":"Pièces jointes","NLS_nextFeature":"Entité suivante","NLS_moreInfo":"Plus d’infos","NLS_searching":"Recherche","NLS_maximize":"Agrandir","NLS_noAttach":"Aucune pièce jointe n'a été trouvée","NLS_noInfo":"Aucune information n'est disponible","NLS_pagingInfo":"(${index} de ${total})","NLS_restore":"Restaurer","NLS_prevFeature":"Entité précédente","NLS_nextMedia":"Support suivant","NLS_close":"Fermer","NLS_zoomTo":"Zoom sur","NLS_prevMedia":"Support précédent"},"HistogramTimeSlider":{"NLS_play":"Lecture/Pause","NLS_fullRange":"plage entière","NLS_range":"Plage","NLS_invalidTimeExtent":"TimeExtent non précisé ou le format est incorrect.","NLS_overview":"APERÇU","NLS_cumulative":"Cumul"},"print":{"NLS_printing":"Impression en cours","NLS_printout":"Impression","NLS_print":"Imprimer"}},"toolbars":{"draw":{"addShape":"Cliquez pour ajouter une forme ou appuyez pour démarrer et relâchez pour terminer","finish":"Double-cliquez pour terminer","invalidType":"Type de géométrie non pris en charge","resume":"Cliquez pour continuer à dessiner","addPoint":"Cliquez pour ajouter un point","freehand":"Appuyez pour commencer et relâchez pour terminer","complete":"Double-cliquez pour exécuter","start":"Cliquez pour commencer à dessiner","addMultipoint":"Cliquez pour commencer à ajouter des points","convertAntiClockwisePolygon":"Les polygones dessinés dans le sens anti-horaire seront inversés pour respecter le sens horaire."},"edit":{"invalidType":"Impossible d'activer l'outil. Vérifiez que l'outil est valide pour le type de géométrie donné.","deleteLabel":"Supprimer"}},"tasks":{"gp":{"gpDataTypeNotHandled":"Type de données GP non géré."},"query":{"invalid":"Impossible d'exécuter la requête. Vérifiez vos paramètres."},"na":{"route":{"routeNameNotSpecified":"RouteName non spécifié pour au moins un arrêt dans FeatureSet."}}},"driveTimes":{"measureLabel":"Mesurer :","toolDefine":"Créer des zones autour de <b>${layername}</b>","itemTags":"Temps de conduite, ${layername}","itemSnippet":"Service d´entité Analyse généré à partir de Créer les temps de conduite","measureHelp":"Pour générer plusieurs zones pour chaque point, tapez les tailles séparées par des espaces (2 3.5 5).","itemDescription":"Service d´entité généré à partir de la solution Créer les temps de conduite.","areaLabel":"Zones depuis différents points :","trafficLabel":"Utiliser les conditions de circulation (facultatif)","resultLabel":"Nom de la couche résultat","outputLayerName":"Conduire à partir de ${layername} : ---"},"analysisTools":{"aggregateTool":"Agréger les points","createDensitySurface":"Créer une surface de densité","sumnearby":"Synthétiser - A proximité","attributeCalculator":"Field Calculator","createBuffers":"Créer des zones tampon","saveResultIn":"Save result in","extractData":"Extraire les données","dataEnrichment":"Enrichissement de données","dissolveBoundaries":"Dissoudre les limites","analyzePatterns":"Analyser la répartition spatiale","findClosestFacility":"Trouver le plus proche","mergeLayers":"Combiner les couches","summarizeWithin":"Synthétiser - A l'intérieur","pubRoleMsg":"Votre compte en ligne n’a pas été attribué au rôle Éditeur.","findLocations":"Déterminer des emplacements","findExistingLocations":"Identifier des emplacements existants","bufferTool":"Données de zone tampon","emptyResultInfoMsg":"Le résultat de votre analyse n’a renvoyé aucune entité. Aucune couche ne sera créée.","invalidServiceName":"Le nom de la couche obtenue contient un ou plusieurs caractères non valides (<, >, #, %, :, \", ?, &, +, / ou \\).","summarizeData":"Synthétiser les données","useMapExtent":"Use current map extent","generateFleetPlan":"Générer un itinéraire de groupe","servNameExists":"You already have a result with this name. Please rename your result and resubmit your analysis.","findHotSpots":"Trouver des points chauds","performAnalysis":"Réaliser l’analyse","createInterpolatedSurface":"Créer une surface","driveTimes":"Create Drive Time Areas","overlayLayers":"Superposer les couches","outputLayerLabel":"Nom de la couche résultat","bufferToolName":"Créer des zones tampon","correlationReporter":"Étudier les corrélations","geoenrichLayer":"Enrichir les entités","findRoute":"Trouver un itinéraire","findNewLocations":"Dériver de nouveaux emplacements","useProximity":"Utiliser la proximité","manageData":"Gérer les données","orgUsrMsg":"Vous devez faire partie d’une entreprise/organisation pour exécuter ce service.","aggregateToolName":"Agréger les points","outputFileName":"Nom du fichier de sortie","invalidServiceNameLength":"The result layer name length should be less than 128 charcaters.","requiredValue":"This value is required."},"common":{"feet":"Pieds","nautMiles":"Miles nautiques","apply":"Appliquer","errorTitle":"Erreur","statistic":"Statistiques","titleLabel":"Titre :","fourLabel":"4.","newLabel":"Nouveau","close":"Fermer","kilometers":"Kilomètres","previous":"Précédente","share":"Partager","runAnalysis":"Exécuter l’analyse","yards":"Yards","yesLabel":"Oui","oneLabel":"1.","ok":"OK","maximum":"Maximum","miles":"Miles","attribute":"Attribut","help":"Aide","comingSoonLabel":"Bientôt !","deleteLabel":"Supprimer","outputnameMissingMsg":"Nom de sortie obligatoire","minimum":"Minimum","remove":"Supprimer","inches":"Pouce(s)","upload":"Télécharger","open":"Ouvrir","submit":"Envoyer","save":"Enregistrer","edit":"Modifier","average":"Moyenne","selectAttribute":"Sélectionner un attribut","sum":"Somme","standardDev":"Écart type","threeLabel":"3.","done":"Terminé","twoLabel":"2.","create":"Créer","warning":"Avertissement","cancel":"Annuler","noLabel":"Non","meters":"Mètres","arcgis":"ArcGIS","pointsUnit":"Point(s)","next":"Suivante","degree":"Degré(s)"},"extractDataTool":{"selectFtrs":"Sélectionner des entités","outputfileName":"NomSortie.zip","itemTags":"Analyse, Extraire les données","itemSnippet":"Élément Fichier d´analyse généré à partir de Extraire les données","clipFtrs":"Services de clip","sameAsLayer":"Identique à ${layername}","itemDescription":"Fichier généré à partir de la solution Extraire des données.","lyrpkg":"Package de couche","outputDataFormat":"Format de données de sortie","sameAsDisplay":"Identique à l´affichage","layersToExtract":"Couches à extraire","studyArea":"Zone d´étude","filegdb":"Base de données géographique sur fichier","shpFile":"Fichier de forme"},"aggregatePointsTool":{"removeAttrStats":"Supprimer les statistiques d’attribut","itemTags":"Résultat d’analyse, Agréger les points, ${pointlayername}, ${polygonlayername}","groupByLabel":"Choisir un attribut selon lequel grouper (en option)","itemSnippet":"Service d’entités Analyse généré à partir de Agréger les points","chooseAreaLabel":"Choisir la couche surfacique","aggregateDefine":"Compter <b>${layername}</b> dans","itemDescription":"Service d’entités généré par l’exécution des solutions Agréger les points. Les points de ${pointlayername} ont été agrégés dans ${polygonlayername}","keepPolygonLabel":"Conserver les polygones sans point","outputLayerName":"Agrégation de ${pointlayername} dans ${polygonlayername}","addStatsLabel":"Ajouter des statistiques (facultatif)"},"io":{"proxyNotSet":"esri.config.defaults.io.proxyUrl n'est pas défini."},"layers":{"FeatureLayer":{"createUserHours":"Créé par ${userId} il y a ${hours} heures","editUserMinutes":"Modifié par ${userId} il y a ${minutes} minutes","editHour":"Modifié il y a une heure","editMinute":"Modifié il y a une minute","editUserMinute":"Modifié par ${userId} il y a une minute","editSeconds":"Modifié il y a quelques secondes","createUserFull":"Créé par ${userId} le ${formattedDate} à ${formattedTime}","editWeekDay":"Modifié ${weekDay} à ${formattedTime}","createUserMinutes":"Créé par ${userId} il y a ${minutes} minutes","createUserHour":"Créé par ${userId} il y a une heure","editUserSeconds":"Modifié par ${userId} il y a quelques secondes","editUserWeekDay":"Modifié par ${userId} ${weekDay} à ${formattedTime}","editUserFull":"Modifié par ${userId} le ${formattedDate} à ${formattedTime}","createFull":"Créé le ${formattedDate} à ${formattedTime}","editUser":"Modifié par ${userId}","noOIDField":"objectIdField n'est pas défini [url: ${url}]","editUserHour":"Modifié par ${userId} il y a une heure","createHour":"Créé il y a une heure","updateError":"une erreur est survenue lors de la mise à jour de la couche","createUserWeekDay":"Créé par ${userId} ${weekDay} à ${formattedTime}","invalidParams":"la requête contient un ou plusieurs paramètres non pris en charge","editHours":"Modifié il y a ${hours} heures","noGeometryField":"impossible de trouver un champ de type 'esriFieldTypeGeometry' dans les informations 'fields' de la couche. Si vous utilisez une couche de service de carte, les entités n'auront pas de géométrie [url: ${url}]","createUserMinute":"Créé par ${userId} il y a une minute","createUser":"Créé par ${userId}","createMinute":"Créé il y a une minute","createMinutes":"Créé il y a ${minutes} minutes","fieldNotFound":"le champ '${field}' est introuvable dans les informations 'fields' de la couche [url: ${url}]","createHours":"Créé il y a ${hours} heures","editUserHours":"Modifié par ${userId} il y a ${hours} heures","editMinutes":"Modifié il y a ${minutes} minutes","createSeconds":"Créé il y a quelques secondes","createUserSeconds":"Créé par ${userId} il y a quelques secondes","createWeekDay":"Créé ${weekDay} à ${formattedTime}","editFull":"Modifié le ${formattedDate} à ${formattedTime}"},"dynamic":{"imageError":"Chargement de l'image impossible"},"tiled":{"tileError":"Chargement de la tuile impossible"},"imageParameters":{"deprecateBBox":"Propriété 'bbox' déconseillée. Utilisez la propriété 'extent'."},"agstiled":{"deprecateRoundrobin":"Option de constructeur 'roundrobin' déconseillée. Utilisez l'option 'tileServers'."},"graphics":{"drawingError":"Affichage du graphique impossible "}},"findHotSpotsTool":{"hotspotsPointDefine":"Analyser <b>${layername}</b> pour rechercher des points chauds et froids statistiquement significatifs ","itemTags":"Résultat d’analyse, Points chauds, ${layername}, ${fieldname}","itemSnippet":"Service d’entités Analyse généré à partir de Trouver des points chauds","defineBoundingLabel":"Définir où des incidents sont possibles","blayerName":"Limites dessinées","Options":"Options","hotspots":"Points chauds","defaultAggregationOption":"Sélectionner des zones d’agrégation","itemDescription":"Service d’entités généré en exécutant la solution Trouver des points chauds","chooseAttributeLabel":"Choisir un champ d’analyse","provideAggLabel":"Définir les zones d'agrégation pour regrouper les incidents","hotspotsPolyDefine":"Analyser <b>${layername}</b> pour rechercher des points chauds et froids statistiquement significatifs de ","defaultBoundingOption":"Sélectionner des zones adjacentes","fieldLabel":"avec ou sans champ d’analyse","noAnalysisField":"Pas de champ d’analyse","outputLayerName":"Points chauds ${layername}"},"geometry":{"deprecateToMapPoint":"esri.geometry.toMapPoint obsolète. Utiliser esri.geometry.toMapGeometry.","deprecateToScreenPoint":"esri.geometry.toScreenPoint obsolète. Utiliser esri.geometry.toScreenGeometry."},"overlayLayersTool":{"itemTags":"Résultat d’analyse, Superposer les couches, ${layername}","unionOutputLyrName":"Union de ${layername} et de ${overlayname}","itemSnippet":"Service d´entités Analyse généré à partir des couches de superposition","eraseOutputLyrName":"Effacement de ${layername} avec ${overlayname}","chooseOverlayMethod":"Choisir la méthode de superposition","itemDescription":"Service d´entités généré avec la solution Croiser les couches.","union":"Agréger","overlayDefine":"Croiser <b>${layername}</b> avec","intersectOutputLyrName":"Intersection de ${layername} et de ${overlayname}","overlayLayerPolyMsg":"La couche de superposition doit être une Couche polygone pour la superposition Union","notSupportedEraseOverlayMsg":"Cette couche de superposition n´est pas prise en charge pour la superposition Effacement. Remplacée par la superposition Intersection.","intersect":"Intersecter","erase":"Effacer","chooseOverlayLayer":"Choisir la couche de superposition"},"arcgis":{"utils":{"geometryServiceError":"Fournissez un service de géométrie pour ouvrir une carte Web.","baseLayerError":"Chargement de la couche de fond de carte impossible"}}},"dojo/cldr/nls/gregorian":{"days-standAlone-short":["dim.","lun.","mar.","mer.","jeu.","ven.","sam."],"months-format-narrow":["J","F","M","A","M","J","J","A","S","O","N","D"],"quarters-standAlone-narrow":["1","2","3","4"],"field-weekday":"jour de la semaine","dateFormatItem-yQQQ":"QQQ y","dateFormatItem-yyQQQQ":"QQQQ yy","dateFormatItem-yyMMMEd":"E d MMM yy","dateFormatItem-yMEd":"E d/M/yyyy","dateFormatItem-MMMEd":"E d MMM","eraNarrow":["av. J.-C.","ap. J.-C."],"dayPeriods-format-wide-morning":"matin","dateFormatItem-MMMdd":"dd MMM","days-format-short":["di","lu","ma","me","je","ve","sa"],"dateTimeFormats-appendItem-Day-Of-Week":"{0} {1}","dateFormat-long":"d MMMM y","months-format-wide":["janvier","février","mars","avril","mai","juin","juillet","août","septembre","octobre","novembre","décembre"],"dateTimeFormat-medium":"{1} {0}","dayPeriods-format-wide-pm":"PM","dateFormat-full":"EEEE d MMMM y","dateFormatItem-Md":"d/M","dayPeriods-format-abbr-am":"AM","dateTimeFormats-appendItem-Second":"{0} ({2}: {1})","dateFormatItem-yMd":"d/M/yyyy","dayPeriods-format-wide-noon":"midi","field-era":"ère","dateFormatItem-yM":"M/yyyy","months-standAlone-wide":["janvier","février","mars","avril","mai","juin","juillet","août","septembre","octobre","novembre","décembre"],"timeFormat-short":"HH:mm","quarters-format-wide":["1er trimestre","2e trimestre","3e trimestre","4e trimestre"],"timeFormat-long":"HH:mm:ss z","field-year":"année","dateFormatItem-yMMM":"MMM y","dateFormatItem-yQ":"'T'Q y","dateTimeFormats-appendItem-Era":"{0} {1}","field-hour":"heure","dateFormatItem-yyyyMMMM":"MMMM y","dateFormatItem-MMdd":"dd/MM","months-format-abbr":["janv.","févr.","mars","avr.","mai","juin","juil.","août","sept.","oct.","nov.","déc."],"dateFormatItem-yyQ":"'T'Q yy","timeFormat-full":"HH:mm:ss zzzz","dateTimeFormats-appendItem-Week":"{0} ({2}: {1})","field-day-relative+0":"aujourd’hui","dayPeriods-format-narrow-morning":"matin","field-day-relative+1":"demain","field-day-relative+2":"après-demain","dateFormatItem-H":"HH","months-standAlone-abbr":["janv.","févr.","mars","avr.","mai","juin","juil.","août","sept.","oct.","nov.","déc."],"quarters-format-abbr":["T1","T2","T3","T4"],"quarters-standAlone-wide":["1er trimestre","2e trimestre","3e trimestre","4e trimestre"],"dateFormatItem-M":"L","days-standAlone-wide":["dimanche","lundi","mardi","mercredi","jeudi","vendredi","samedi"],"dateFormatItem-yyMMM":"MMM yy","timeFormat-medium":"HH:mm:ss","dateFormatItem-Hm":"HH:mm","quarters-standAlone-abbr":["T1","T2","T3","T4"],"eraAbbr":["av. J.-C.","ap. J.-C."],"field-minute":"minute","field-dayperiod":"cadran","days-standAlone-abbr":["dim.","lun.","mar.","mer.","jeu.","ven.","sam."],"dayPeriods-format-wide-night":"soir","dateFormatItem-yyMMMd":"d MMM yy","dateFormatItem-d":"d","dateFormatItem-ms":"mm:ss","quarters-format-narrow":["1","2","3","4"],"field-day-relative+-1":"hier","dateFormatItem-h":"h a","dateTimeFormat-long":"{1} {0}","dayPeriods-format-narrow-am":"AM","field-day-relative+-2":"avant-hier","dateFormatItem-MMMd":"d MMM","dateFormatItem-MEd":"E d/M","dateTimeFormat-full":"{1} {0}","field-day":"jour","days-format-wide":["dimanche","lundi","mardi","mercredi","jeudi","vendredi","samedi"],"field-zone":"fuseau horaire","dateTimeFormats-appendItem-Day":"{0} ({2}: {1})","dateFormatItem-y":"y","months-standAlone-narrow":["J","F","M","A","M","J","J","A","S","O","N","D"],"field-year-relative+-1":"l’année dernière","dayPeriods-format-narrow-night":"soir","field-month-relative+-1":"le mois dernier","dateFormatItem-yyMM":"MM/yy","dateFormatItem-hm":"h:mm a","dateTimeFormats-appendItem-Year":"{0} {1}","dateTimeFormats-appendItem-Hour":"{0} ({2}: {1})","dayPeriods-format-abbr-pm":"PM","days-format-abbr":["dim.","lun.","mar.","mer.","jeu.","ven.","sam."],"dateFormatItem-yMMMd":"d MMM y","eraNames":["avant Jésus-Christ","après Jésus-Christ"],"days-format-narrow":["D","L","M","M","J","V","S"],"days-standAlone-narrow":["D","L","M","M","J","V","S"],"dateFormatItem-MMM":"LLL","field-month":"mois","dateTimeFormats-appendItem-Quarter":"{0} ({2}: {1})","dayPeriods-format-wide-am":"AM","dateTimeFormats-appendItem-Month":"{0} ({2}: {1})","dateTimeFormats-appendItem-Minute":"{0} ({2}: {1})","dateFormatItem-MMMMEd":"E d MMMM","dateFormat-short":"dd/MM/yy","dateFormatItem-MMd":"d/MM","dayPeriods-format-wide-afternoon":"après-midi","dayPeriods-format-narrow-noon":"midi","field-second":"seconde","dateFormatItem-yMMMEd":"E d MMM y","field-month-relative+0":"ce mois-ci","field-month-relative+1":"le mois prochain","dateFormatItem-Ed":"E d","dateTimeFormats-appendItem-Timezone":"{0} {1}","field-week":"semaine","dateFormat-medium":"d MMM y","field-year-relative+0":"cette année","field-week-relative+-1":"la semaine dernière","field-year-relative+1":"l’année prochaine","dayPeriods-format-narrow-pm":"p","dateTimeFormat-short":"{1} {0}","dateFormatItem-Hms":"HH:mm:ss","dateFormatItem-hms":"h:mm:ss a","field-week-relative+0":"cette semaine","field-week-relative+1":"la semaine prochaine"},"dijit/nls/loading":{"loadingState":"Chargement...","errorState":"Une erreur est survenue"},"dojo/cldr/nls/number":{"scientificFormat":"#E0","currencySpacing-afterCurrency-currencyMatch":"[:letter:]","infinity":"∞","list":";","percentSign":"%","minusSign":"-","currencySpacing-beforeCurrency-surroundingMatch":"[:digit:]","decimalFormat-short":"000 Bn","currencySpacing-afterCurrency-insertBetween":" ","nan":"NaN","plusSign":"+","currencySpacing-afterCurrency-surroundingMatch":"[:digit:]","currencyFormat":"#,##0.00 ¤;(#,##0.00 ¤)","currencySpacing-beforeCurrency-currencyMatch":"[:letter:]","perMille":"‰","group":" ","percentFormat":"#,##0 %","decimalFormat-long":"000 billions","decimalFormat":"#,##0.###","decimal":",","currencySpacing-beforeCurrency-insertBetween":" ","exponential":"E"},"dijit/nls/common":{"buttonOk":"OK","buttonCancel":"Annuler","buttonSave":"Enregistrer","itemClose":"Fermer"}});