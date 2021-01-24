	.data

	true_print_string: .asciiz "true"
	false_print_string: .asciiz "false"

	aa: .word 0
	ab: .word 0
	ac: .word 0
	ad: .word 0
	ae: .word 0
	af: .word 0
	ag: .word 0
	_ah: .word 79
	_ai: .word 3
	_aj: .word 4
	_ak: .word 0
	_al: .word 3
	_am: .word 3
	_an: .word 1
	_ao: .word 1078523331
	_ap: .word 1078523331
	_aq: .word 1
	_ar: .word 1067702026
	_as: .word 0
	_at: .word 10
	_au: .word 10
	_av: .word 1

	.text
	.globl main

test:

	# Assigning _ah to ab
	lw $a0, _ah
	la $a1, ab
	sw $a0, 0($a1)
	
	# Printing ab
	li $v0, 1
	lw $a0, ab
	syscall
	
	# Printing new line
	li $a0, 0xA
	li $v0, 0xB
	syscall
	
main:

	# Assigning _ai to ac
	lw $a0, _ai
	la $a1, ac
	sw $a0, 0($a1)
	
	# Assigning _aj to ad
	lw $a0, _aj
	la $a1, ad
	sw $a0, 0($a1)
	
	# Is ac equal to ad? 
	lw $a0, ac
	lw $a1, ad
	seq $t0, $a0, $a1
	la $a2, _ak
	sw $t0, 0($a2)
	
	# Printing _ak
	li $v0, 4
	la $a0, false_print_string
	syscall
	
	# Printing new line
	li $a0, 0xA
	li $v0, 0xB
	syscall
	
	# Assigning _al to ac
	lw $a0, _al
	la $a1, ac
	sw $a0, 0($a1)
	
	# Assigning _am to ad
	lw $a0, _am
	la $a1, ad
	sw $a0, 0($a1)
	
	# Is ac equal to ad? 
	lw $a0, ac
	lw $a1, ad
	seq $t0, $a0, $a1
	la $a2, _an
	sw $t0, 0($a2)
	
	# Printing _an
	li $v0, 4
	la $a0, true_print_string
	syscall
	
	# Printing new line
	li $a0, 0xA
	li $v0, 0xB
	syscall
	
	# Assigning _ao to ae
	lwc1 $f0, _ao
	la $a0, ae
	swc1 $f0, 0($a0)
	
	# Assigning _ap to af
	lwc1 $f0, _ap
	la $a0, af
	swc1 $f0, 0($a0)
	
	# Is ae equal to af? 
	lw $a0, ae
	lw $a1, af
	seq $t0, $a0, $a1
	la $a2, _aq
	sw $t0, 0($a2)
	
	# Printing _aq
	li $v0, 4
	la $a0, true_print_string
	syscall
	
	# Printing new line
	li $a0, 0xA
	li $v0, 0xB
	syscall
	
	# Assigning _ar to ae
	lwc1 $f0, _ar
	la $a0, ae
	swc1 $f0, 0($a0)
	
	# Is ae equal to af? 
	lw $a0, ae
	lw $a1, af
	seq $t0, $a0, $a1
	la $a2, _as
	sw $t0, 0($a2)
	
	# Printing _as
	li $v0, 4
	la $a0, false_print_string
	syscall
	
	# Printing new line
	li $a0, 0xA
	li $v0, 0xB
	syscall
	
	# Is _at equal to _au? 
	lw $a0, _at
	lw $a1, _au
	seq $t0, $a0, $a1
	la $a2, _av
	sw $t0, 0($a2)
	
	# Assigning _av to ag
	lw $a0, _av
	la $a1, ag
	sw $a0, 0($a1)
	
	# Printing ag
	li $v0, 4
	la $a0, true_print_string
	syscall
	
	# Printing new line
	li $a0, 0xA
	li $v0, 0xB
	syscall
	
	# Exit!
	li $v0, 10
	syscall
	
	
