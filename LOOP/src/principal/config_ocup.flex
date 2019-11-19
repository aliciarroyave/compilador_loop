package principal;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.runtime.Symbol;
import objetos.token;

%%
%class lexico
%{
    ArrayList<String> tokens = new ArrayList<>();
    int tab = 0;
    private int getTab(){
        int tabaux = tab;
        tab = 0;
        return tabaux;
   }
%}
%unicode
%line
%column
%cup
%eofval{
  try {
            BufferedWriter archivoSalida= new BufferedWriter(new FileWriter(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + File.separator + "LOOP" + File.separator + "tokens.txt"));
            if (!tokens.isEmpty()) {
                for(String token:tokens ){
                    archivoSalida.write(token);
                    archivoSalida.newLine();
                }
            }
            archivoSalida.close();
    } catch (IOException ex) {
        Logger.getLogger(Semantico.class.getName()).log(Level.SEVERE, null, ex);
    }
  System.out.println("Fin de archivo encontrado");
  return new Symbol(sym.EOF);
%eofval}
%eofclose

/*            ESTADOS               */
loop     =  {CaracterValido2}+\.loop

/*     CARACTERES ESPECIALES        */
FinDeLinea      = \r|\n|\r\n
Espacio         = \s
Tab             = {Espacio}{2}
Ignorar         = {Tab}*{FinDeLinea}|{Espacio}{1,3}{FinDeLinea}|({Tab}|{Espacio}{1,3}){FinDeLinea}*
CaracterEntrada = [^(\r|\n|\r\n)]
CaracterValido  = [^("+"|"-"|"/"|"%"|"#"|"^"|\"|"."|","|\s|"@"|"("|")"|"$"|"|"|":"|";"|"["|"]"|"{"|"}")]
CaracterValido2  = [^(\"|\s|"$"|";"|"["|"]"|"|"|"{"|"}"|"<"|">")]

/*     COMENTARIOS                  */
Coment1 = "//"{CaracterEntrada}*
Coment2 = "/*"({CaracterEntrada}*{FinDeLinea}*)*"*/"
Coments = {Espacio}*({Coment1}|{Coment2})

/*    VARIABLES                     */
IdVar = [a-záéíóúñ]{CaracterValido}*
IdClase = [A-ZÑ]{CaracterValido}*
/*    TIPO DE DATO     */
Entero = [1-9][0-9]*
Real   = -?((0|{Entero})(\.(0|{Entero}[1-9]))?)
Cadena   = \".*\"
%%
/* comentarios */
{Coments}       {/* IGNORAR */}
/*  PALABRA RESERVADAS */
/*"funcion"     {tokens.add("");return new Symbol(sym.RFUNCION, new token(yytext(), yyline, yycolumn, getTab()));}  */
"cadena"        {tokens.add("cadena");return new Symbol(sym.RCADENA, new token(yytext(), yyline, yycolumn, getTab()));} //si
"boleano"       {tokens.add("boleano");return new Symbol(sym.RBOLEANO, new token(yytext(), yyline, yycolumn, getTab()));} //si
"real"          {tokens.add("real");return new Symbol(sym.RREAL, new token(yytext(), yyline, yycolumn, getTab()));}  //si
"entero"        {tokens.add("entero");return new Symbol(sym.RENTERO, new token(yytext(), yyline, yycolumn, getTab()));} //si
"incluir"       {tokens.add("incluir");return new Symbol(sym.RINCLUIR, new token(yytext(), yyline, yycolumn, getTab()));} //si
/*"codigo"      {tokens.add("");return new Symbol(sym.RCODIGO, new token(yytext(), yyline, yycolumn, getTab()));} */
"si"            {tokens.add("si");return new Symbol(sym.RSI, new token(yytext(), yyline, yycolumn, getTab()));} //si
"sino"          {tokens.add("sino");return new Symbol(sym.RSINO, new token(yytext(), yyline, yycolumn, getTab()));} //si
"entonces"      {tokens.add("entonces");return new Symbol(sym.RENTONCES, new token(yytext(), yyline, yycolumn, getTab()));} //si
"devolver"      {tokens.add("devolver");return new Symbol(sym.RRETORNAR, new token(yytext(), yyline, yycolumn, getTab()));} //si
"false"         {tokens.add("false");return new Symbol(sym.RFALSE, new token(yytext(), yyline, yycolumn, getTab()));} //si
"true"          {tokens.add("true");return new Symbol(sym.RTRUE, new token(yytext(), yyline, yycolumn, getTab()));} //si
"paracada"      {tokens.add("paracada");return new Symbol(sym.RPARACADA, new token(yytext(), yyline, yycolumn, getTab()));} 
"desde"         {tokens.add("desde");return new Symbol(sym.RDESDE, new token(yytext(), yyline, yycolumn, getTab()));} //si
"nulo"          {tokens.add("nulo");return new Symbol(sym.RNULO, new token(yytext(), yyline, yycolumn, getTab()));} //si
"mientras"      {tokens.add("mientras");return new Symbol(sym.RMIENTRAS, new token(yytext(), yyline, yycolumn, getTab()));} //si
"incrementar"   {tokens.add("incrementar");return new Symbol(sym.RINCREMENTAR, new token(yytext(), yyline, yycolumn, getTab()));} //si
"decrementar"   {tokens.add("decrementar");return new Symbol(sym.RDECREMENTAR, new token(yytext(), yyline, yycolumn, getTab()));} //si
"hacer"         {tokens.add("hacer");return new Symbol(sym.RHACER, new token(yytext(), yyline, yycolumn, getTab()));} //si
/*"iterar"      {tokens.add("");return new Symbol(sym.RITERAR, new token(yytext(), yyline, yycolumn, getTab()));}
"en"            {tokens.add("en");return new Symbol(sym.REN, new token(yytext(), yyline, yycolumn, getTab()));} 
"variables"     {tokens.add("variables");return new Symbol(sym.RVARIABLES, new token(yytext(), yyline, yycolumn, getTab()));}
"principal"     {tokens.add("principal");return new Symbol(sym.RPRINCIPAL, new token(yytext(), yyline, yycolumn, getTab()));}
"leer"          {tokens.add("leer");return new Symbol(sym.RLEER, new token(yytext(), yyline, yycolumn, getTab()));} */
"escribir"      {tokens.add("escribir");return new Symbol(sym.RESCRIBIR, new token(yytext(), yyline, yycolumn, getTab()));} //si
 
"clase"         {tokens.add("clase");return new Symbol(sym.RCLASE, new token(yytext(), yyline, yycolumn, getTab()));} //si
"propiedades"   {tokens.add("propiedades");return new Symbol(sym.RPROP, new token(yytext(), yyline, yycolumn, getTab()));} //si
"metodos"       {tokens.add("metodos");return new Symbol(sym.RMET, new token(yytext(), yyline, yycolumn, getTab()));} //si
"publicas"      {tokens.add("publicas");return new Symbol(sym.RPUBA, new token(yytext(), yyline, yycolumn, getTab()));} //si
"privadas"      {tokens.add("privadas");return new Symbol(sym.RPRIVA, new token(yytext(), yyline, yycolumn, getTab()));} //si
"protegidas"    {tokens.add("protegidas");return new Symbol(sym.RPROTA, new token(yytext(), yyline, yycolumn, getTab()));} //si
"publicos"      {tokens.add("publicos");return new Symbol(sym.RPUBLO, new token(yytext(), yyline, yycolumn, getTab()));} //si
"privados"      {tokens.add("privados");return new Symbol(sym.RPRIVO, new token(yytext(), yyline, yycolumn, getTab()));} //si
"protegidos"    {tokens.add("protegidos");return new Symbol(sym.RPROTO, new token(yytext(), yyline, yycolumn, getTab()));} //si
"Principal"     {tokens.add("Principal");return new Symbol(sym.RPRINCIPAL, new token(yytext(), yyline, yycolumn, getTab()));} //si
"intanciar"     {tokens.add("intanciar");return new Symbol(sym.RISNTAN, new token(yytext(), yyline, yycolumn, getTab()));} //si
"eliminar"      {tokens.add("eliminar");return new Symbol(sym.RELIM, new token(yytext(), yyline, yycolumn, getTab()));} //si
"constructor"   {tokens.add("constructor");return new Symbol(sym.RCONSTRUC, new token(yytext(), yyline, yycolumn, getTab()));} //si
"extiende"      {tokens.add("extiende");return new Symbol(sym.REXTEND, new token(yytext(), yyline, yycolumn, getTab()));} //si
"arg"           {tokens.add("arg");return new Symbol(sym.RARG, new token(yytext(), yyline, yycolumn, getTab()));} //si

/*      OPERADORES     */
"+"             {tokens.add("+");return new Symbol(sym.SUMA, new token(yytext(), yyline, yycolumn, getTab()));} 
"-"             {tokens.add("-");return new Symbol(sym.RESTA, new token(yytext(), yyline, yycolumn, getTab()));} 
"*"             {tokens.add("*");return new Symbol(sym.MULTIPLICACION, new token(yytext(), yyline, yycolumn, getTab()));} 
"/"             {tokens.add("/");return new Symbol(sym.DIVISION, new token(yytext(), yyline, yycolumn, getTab()));} 
"^"             {tokens.add("^");return new Symbol(sym.EXPONENTE, new token(yytext(), yyline, yycolumn, getTab()));} 
"%"             {tokens.add("%");return new Symbol(sym.MODULACION, new token(yytext(), yyline, yycolumn, getTab()));} 
"="             {tokens.add("=");return new Symbol(sym.IGUALADOR, new token(yytext(), yyline, yycolumn, getTab()));} 
"=="            {tokens.add("==");return new Symbol(sym.COMPARADOR, new token(yytext(), yyline, yycolumn, getTab()));} 
"!="            {tokens.add("!=");return new Symbol(sym.DIFERENTE, new token(yytext(), yyline, yycolumn, getTab()));} 
"<="            {tokens.add("<=");return new Symbol(sym.MENORIGUAL, new token(yytext(), yyline, yycolumn, getTab()));} 
">="            {tokens.add(">=");return new Symbol(sym.MAYORIGUAL, new token(yytext(), yyline, yycolumn, getTab()));} 
">"             {tokens.add(">");return new Symbol(sym.MAYOR, new token(yytext(), yyline, yycolumn, getTab()));} 
"<"             {tokens.add("<");return new Symbol(sym.MENOR, new token(yytext(), yyline, yycolumn, getTab()));} 
"?"             {tokens.add("?");return new Symbol(sym.INTERROGANTE, new token(yytext(), yyline, yycolumn, getTab()));} 
"AND"           {tokens.add("AND");return new Symbol(sym.AND, new token(yytext(), yyline, yycolumn, getTab()));} 
"OR"            {tokens.add("OR");return new Symbol(sym.OR, new token(yytext(), yyline, yycolumn, getTab()));} 
"."             {tokens.add(".");return new Symbol(sym.PUNTO, new token(yytext(), yyline, yycolumn, getTab()));} 


/*        OTROS        */
":"             {tokens.add(":");return new Symbol(sym.PUNTOPUNTO, new token(yytext(), yyline, yycolumn, getTab()));} 
";"             {tokens.add(";");return new Symbol(sym.PUNTOCOMA, new token(yytext(), yyline, yycolumn, getTab()));} 
"("             {tokens.add("(");return new Symbol(sym.PARENTESISA, new token(yytext(), yyline, yycolumn, getTab()));} 
")"             {tokens.add(")");return new Symbol(sym.PARENTESISC, new token(yytext(), yyline, yycolumn, getTab()));} 
"["             {tokens.add("[");return new Symbol(sym.CORCHETEA, new token(yytext(), yyline, yycolumn, getTab()));} 
"]"             {tokens.add("]");return new Symbol(sym.CORCHETEC, new token(yytext(), yyline, yycolumn, getTab()));} 
","             {tokens.add(",");return new Symbol(sym.COMA, new token(yytext(), yyline, yycolumn, getTab()));} 

/*        REGALAS      */    
{Tab}           {tokens.add("tab");tab++;} /*identacion*/   
{Cadena}        {tokens.add("cadena");return new Symbol(sym.CADENA, new token(yytext(), yyline, yycolumn, getTab()));} 
{Entero}        {tokens.add("entero");return new Symbol(sym.ENTERO, new token(yytext(), yyline, yycolumn, getTab()));} 
{Real}          {tokens.add("real");return new Symbol(sym.REAL, new token(yytext(), yyline, yycolumn, getTab()));} 
{IdVar}         {tokens.add("IdVar");return new Symbol(sym.IDVAR, new token(yytext(), yyline, yycolumn, getTab()));} 
{IdClase}       {tokens.add("IdClase");return new Symbol(sym.IDCLASE, new token(yytext(), yyline, yycolumn, getTab()));} 
{loop}          {tokens.add("loop");return new Symbol(sym.LOOP, new token(yytext(), yyline, yycolumn, getTab()));}

{Ignorar}       {/* IGNORAR */}
.               {System.err.println("caracter invalido \"" + yytext() + "\"["+ yyline + ":"+ yycolumn + ":"+ yychar + "]");
                    return new Symbol(sym.error);}