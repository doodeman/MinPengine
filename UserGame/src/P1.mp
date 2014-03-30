{
	EntityName: P1,
	EntityType: player,
	Gravity: 1,
	Collidable: true,
	onCollide: {default: stop, character: destroy},
	size: {0.8,0.8}
	moveSpeed: 1,
	graphics: 'assets/p1.png',
} 