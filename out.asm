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
	ah: .word 0
	_ai: .word 79
	_aj: .word 3
	_ak: .word 4
	_al: .word 0
	_am: .word 3
	_an: .word 3
	_ao: .word 1
	_ap: .word 1078523331
	_aq: .word 1078523331
	_ar: .word 1
	_as: .word 1067702026
	_at: .word 0
	_au: .word 10
	_av: .word 10
	_aw: .word 1
	_ax: .word 1
	_ay: .word 0

	.text
	.globl main

test:

	# Assigning _ai to ab
	lw $a0, _ai
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

	# Assigning _aj to ac
	lw $a0, _aj
	la $a1, ac
	sw $a0, 0($a1)
	
	# Assigning _ak to ad
	lw $a0, _ak
	la $a1, ad
	sw $a0, 0($a1)
	
	# Is ac equal to ad? 
	lw $a0, ac
	lw $a1, ad
	seq $t0, $a0, $a1
	la $a2, _al
	sw $t0, 0($a2)
	
	# Printing _al
	li $v0, 4
	la $a0, false_print_string
	syscall
	
	# Printing new line
	li $a0, 0xA
	li $v0, 0xB
	syscall
	
	# Assigning _am to ac
	lw $a0, _am
	la $a1, ac
	sw $a0, 0($a1)
	
	# Assigning _an to ad
	lw $a0, _an
	la $a1, ad
	sw $a0, 0($a1)
	
	# Is ac equal to ad? 
	lw $a0, ac
	lw $a1, ad
	seq $t0, $a0, $a1
	la $a2, _ao
	sw $t0, 0($a2)
	
	# Printing _ao
	li $v0, 4
	la $a0, true_print_string
	syscall
	
	# Printing new line
	li $a0, 0xA
	li $v0, 0xB
	syscall
	
	# Assigning _ap to ae
	lwc1 $f0, _ap
	la $a0, ae
	swc1 $f0, 0($a0)
	
	# Assigning _aq to af
	lwc1 $f0, _aq
	la $a0, af
	swc1 $f0, 0($a0)
	
	# Is ae equal to af? 
	lw $a0, ae
	lw $a1, af
	seq $t0, $a0, $a1
	la $a2, _ar
	sw $t0, 0($a2)
	
	# Printing _ar
	li $v0, 4
	la $a0, true_print_string
	syscall
	
	# Printing new line
	li $a0, 0xA
	li $v0, 0xB
	syscall
	
	# Assigning _as to ae
	lwc1 $f0, _as
	la $a0, ae
	swc1 $f0, 0($a0)
	
	# Is ae equal to af? 
	lw $a0, ae
	lw $a1, af
	seq $t0, $a0, $a1
	la $a2, _at
	sw $t0, 0($a2)
	
	# Printing _at
	li $v0, 4
	la $a0, false_print_string
	syscall
	
	# Printing new line
	li $a0, 0xA
	li $v0, 0xB
	syscall
	
	# Is _au equal to _av? 
	lw $a0, _au
	lw $a1, _av
	seq $t0, $a0, $a1
	la $a2, _aw
	sw $t0, 0($a2)
	
	# Assigning _aw to ag
	lw $a0, _aw
	la $a1, ag
	sw $a0, 0($a1)
	
	# Assigning _ax to ah
	lw $a0, _ax
	la $a1, ah
	sw $a0, 0($a1)
	
	# Printing ag
	li $v0, 4
	la $a0, true_print_string
	syscall
	
	# Printing new line
	li $a0, 0xA
	li $v0, 0xB
	syscall
	
	# Is ag equal to ah? 
	lw $a0, ag
	lw $a1, ah
	seq $t0, $a0, $a1
	la $a2, _ay
	sw $t0, 0($a2)
	
	# Printing _ay
	li $v0, 4
	la $a0, false_print_string
	syscall
	
	# Printing new line
	li $a0, 0xA
	li $v0, 0xB
	syscall
	
	# Exit!
	li $v0, 10
	syscall
	
	
