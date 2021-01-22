	.data

	aa: .word 0
	ab: .word 0
	ac: .word 0
	_ad: .word 5
	_ae: .word 10
	af: .word 0
	ag: .word 0
	_ah: .word 5

	.text
	.globl main

main:

	lw $a0, _ad
	la $a1, aa
	move $a2, $a0
	sw $a2, 0($a1)
	lw $a0, _ae
	la $a1, ab
	move $a2, $a0
	sw $a2, 0($a1)
	
test:

	lw $a0, _ah
	la $a1, ag
	move $a2, $a0
	sw $a2, 0($a1)
	
