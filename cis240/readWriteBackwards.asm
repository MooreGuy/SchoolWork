mov ax,2000   ;set data segment
mov ds,ax
mov si,1100   ;point to storage location
mov ah,1            ;read command /108 WAITFORLF TOP
int 21              ;read one char into AL
CMP AL,0D     ;test for line feed
JE 115        ;if linefeed exit loop
MOV [SI],AL   ;save character
INC SI        ;point to next character
jmp 108            ;jmp to waitforlf top
mov bx,1000         ;set base of data tables
sub si,1100   ;find number of entries    
mov cx,si
mov si,0100   ;point to beginnging of table
add si,cx     ;move the pointer to the end of the string
sub si,1      ;add one since it was offset from the carriage return
mov ah,02      ;write monitor command
mov dl,0a     ; carriage return
int 21        ;write to monitor
mov ah,02     ;write command / 0121 WRITELOOPTOP
mov dl,[bx+si]    ;get byte for write command
int 21            ;write to monitor
dec si
dec cx
jnz 012C      ;jump to writelooptop
int 3         ;break