{
	EntityName: C1,
	EntityType: character,
	Gravity: 1,
	Collidable: true,
	onCollide: {default: reverseDirection, player: destroyOther, projectile: destroy},
	size: {0.8,0.8}
	moveSpeed: 1,
	onFutureFall: {default: reverseDirection},
	graphics: 'assets/c1.png',
} 