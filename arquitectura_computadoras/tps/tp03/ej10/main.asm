0000117d <fibonacci>:
    117d:	55                   	push   ebp
    117e:	89 e5                	mov    ebp,esp
    1180:	53                   	push   ebx

    1181:	83 ec 04             	sub    esp,0x4
    1184:	e8 96 00 00 00       	call   121f <__x86.get_pc_thunk.ax>
    1189:	05 6b 2e 00 00       	add    eax, 0x2e6b 

	118e:	83 7d 08 01          	cmp    DWORD PTR [ebp+0x8],0x1
    1192:	7f 07                	jg     119b <fibonacci+0x1e>	; continue

    1194:	b8 01 00 00 00       	mov    eax,0x1
    1199:	eb 28                	jmp    11c3 <fibonacci+0x46>	; end
    
	119b:	8b 45 08             	mov    eax,DWORD PTR [ebp+0x8]

    119e:	83 e8 01             	sub    eax,0x1

    11a1:	83 ec 0c             	sub    esp,0xc
    11a4:	50                   	push   eax
    11a5:	e8 d3 ff ff ff       	call   117d <fibonacci>
    11aa:	83 c4 10             	add    esp,0x10
    11ad:	89 c3                	mov    ebx,eax

    11af:	8b 45 08             	mov    eax,DWORD PTR [ebp+0x8]
    11b2:	83 e8 02             	sub    eax,0x2
    11b5:	83 ec 0c             	sub    esp,0xc
    11b8:	50                   	push   eax
    11b9:	e8 bf ff ff ff       	call   117d <fibonacci>

    11be:	83 c4 10             	add    esp,0x10
    11c1:	01 d8                	add    eax,ebx
    11c3:	8b 5d fc             	mov    ebx,DWORD PTR [ebp-0x4]
    11c6:	c9                   	leave
    11c7:	c3                   	ret

