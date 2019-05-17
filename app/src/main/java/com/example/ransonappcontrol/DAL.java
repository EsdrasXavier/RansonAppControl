package com.example.ransonappcontrol;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

class DAL {
    private static final String TAG = "DAL";

    private SQLiteDatabase db;
    private CreateDatabase database;

    DAL(Context context) {
        database = new CreateDatabase(context);
    }


    boolean insert(Patient p) {
        ContentValues values;
        long result;

        // Obtemos um acesso ao banco com permissão de escrita
        db = database.getWritableDatabase();

        // Par de nomes de colunas + valores, para atualização no banco
        values = new ContentValues();
        values.put(CreateDatabase.NAME, p.getName());
        values.put(CreateDatabase.AGE, p.getAge());
        values.put(CreateDatabase.LITIASE_BILIAR, p.isHasBiliaryLithiasis());
        values.put(CreateDatabase.LEUCOCITOS , p.getLeukocytesNumber());
        values.put(CreateDatabase.GLICEMIA, p.getBloodGlucoseValue());
        values.put(CreateDatabase.AST_TGO, p.getAstAndTgoValue());
        values.put(CreateDatabase.LDH, p.getLdhValue());
        values.put(CreateDatabase.POINTS, p.getPoints());
        values.put(CreateDatabase.DEATH, p.getDeath());

        // efetivamente insere o registro no banco, fechando o acesso em seguida
        result = db.insert(CreateDatabase.TABLE, null, values);
        db.close();

        // Reporta um erro caso tenha acontecido
        if (result == -1) {
            Log.e(TAG, "insert: Erro inserindo registro");
            return false;
        }

        return true;
    }

    /**
     * Remove um registro do banco.
     * @param id O id do registro a ser removido
     * @return true em caso de sucesso, false em caso contrário
     */
    boolean delete(int id) {
        long result;

        // A cláusula where para o update. Note a interrogação. É um "wildcard".
        // Seu valor será inserido pelo contido na variável args
        String where = "_id = ?";
        String[] args = { String.valueOf(id) };

        // Obtemos um acesso ao banco com permissão de escrita
        db = database.getWritableDatabase();

        // efetivamente faz o delete no banco, fechando o acesso em seguida
        result = db.delete(CreateDatabase.TABLE, where, args);
        db.close();

        // Reporta um erro caso tenha acontecido
        if (result == -1) {
            Log.e(TAG, "insert: Erro removendo registro");
            return false;
        }

        return true;
    }


    /**
     * Busca no banco um registro pelo id.
     * @param id id do livro.
     * @return Cursor apontando para o registro do banco que contém o id indicado.
     */
    Cursor findById(int id) {
        Cursor cursor;

        // A cláusula where para o update. Note a interrogação. É um "wildcard".
        // Seu valor será inserido pelo contido na variável args
        String where = "_id = ?";
        String[] args = { String.valueOf(id) };

        // Obtém um acesso ao banco somente leitura
        db = database.getReadableDatabase();

        // Cria uma consulta ao banco. Observe o formato. Ao não passar colunas,
        // temos o equivalente a SELECT *. Esta consulta, em SQL corrente, seria:
        // SELECT * from book WHERE _id = :id (:id é o id passado por parâmetro)
        cursor = db.query(CreateDatabase.TABLE, null,
                where, args, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }

    Cursor loadAll() {
        Cursor cursor;
        String[] fields = {CreateDatabase.ID, CreateDatabase.NAME};
        db = database.getReadableDatabase();

        // SELECT _id, title FROM book
        // Consulta equivalente:
        // String sql = "SELECT _id, title FROM book";
        // cursor = db.rawQuery(sql, null);
        cursor = db.query(CreateDatabase.TABLE, null, null,
                null, null, null,
                null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }
}