{
	EntityName: P1,
	EntityType: player,
	Gravity: 1,
	Collidable: true,
	onCollide: {default: stop, character: destroy},
	Size: {0.8,0.8}
	moveSpeed: 1,
	Graphics: 'assets/p1.png',
	Controls: {UpArrow: jump, DownArrow: crouch, LeftArrow: moveLeft, RightArrow: moveRight}
} 